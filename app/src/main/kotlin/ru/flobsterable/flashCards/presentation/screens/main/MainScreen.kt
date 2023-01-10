package ru.flobsterable.flashCards.presentation.screens.main

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.work.WorkInfo
import ru.flobsterable.flashCards.R
import ru.flobsterable.flashCards.presentation.screens.main.components.MainComponent
import ru.flobsterable.flashCards.presentation.screens.main.models.MainEvent
import ru.flobsterable.flashCards.presentation.screens.main.models.MainViewModel
import ru.flobsterable.flashCards.workers.WORK_ID
import ru.flobsterable.flashCards.workers.Worker

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsState()
    val isLoading = remember { mutableStateOf(false) }
    val worker = remember { Worker(context) }

    val workInfo = worker.workManager
        .getWorkInfosForUniqueWorkLiveData(WORK_ID)
        .observeAsState()
        .value

    val downloadInfo = remember(key1 = workInfo) { workInfo?.find { it.id == worker.downloadRequest.id } }
    val unzipInfo = remember(key1 = workInfo) { workInfo?.find { it.id == worker.unzipRequest.id } }

    when (downloadInfo?.state) {
        WorkInfo.State.FAILED -> {
            ShowErrorToast()
            isLoading.value = false
        }
        WorkInfo.State.CANCELLED -> { isLoading.value = false }
        else -> {}
    }
    when (unzipInfo?.state) {
        WorkInfo.State.SUCCEEDED -> {
            viewModel.sendEvent(MainEvent.CheckData)
            isLoading.value = false
        }
        WorkInfo.State.FAILED -> {
            ShowErrorToast()
            isLoading.value = false
        }
        WorkInfo.State.CANCELLED -> { isLoading.value = false }
        else -> {}
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState.value.dataTitleName != null) {
            true -> {
                MainComponent(
                    titleText = uiState.value.dataTitleName!!,
                    buttonText = stringResource(id = R.string.button_start),
                ) { viewModel.sendEvent(MainEvent.OpenSwipeCards) }
            }
            false -> {
                when (isLoading.value) {
                    true -> {
                        MainComponent(
                            titleText = stringResource(id = R.string.title_downloading),
                            buttonText = stringResource(id = R.string.button_cancel),
                        ) { worker.cancelDownloadingFile() }
                    }
                    false -> {
                        MainComponent(
                            titleText = stringResource(id = R.string.title_text),
                            buttonText = stringResource(id = R.string.button_download),
                        ) {
                            isLoading.value = true
                            worker.downloadFile()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShowErrorToast() {
    Toast.makeText(
        LocalContext.current,
        stringResource(id = R.string.toast_error_message),
        Toast.LENGTH_LONG
    ).show()
}

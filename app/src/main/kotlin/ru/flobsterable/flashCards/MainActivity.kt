package ru.flobsterable.flashCards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import dagger.hilt.android.AndroidEntryPoint
import ru.flobsterable.flashCards.ui.theme.FlashСardsTheme
import ru.flobsterable.flashCards.workers.LoaderWorker
import ru.flobsterable.flashCards.workers.UnzipWorker

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FlashСardsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Button(onClick = {
                        getFile()
                    },) { Text(text = "go")}
                }
            }
        }
    }

    private fun getFile() {
        WorkManager.getInstance(this).beginUniqueWork(
            "unique_work", ExistingWorkPolicy.KEEP,
            OneTimeWorkRequest.from(LoaderWorker::class.java)
        ).then(
            OneTimeWorkRequest.from(UnzipWorker::class.java)
        ).enqueue()
    }
}

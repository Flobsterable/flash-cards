package ru.flobsterable.flashCards.workers

import android.content.Context
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.flobsterable.flashCards.R
import ru.flobsterable.flashCards.data.repository.Repository
import ru.flobsterable.flashCards.data.utils.unzip
import ru.flobsterable.flashCards.notification.processNotificationBuilder
import java.io.IOException

@HiltWorker
class UnzipWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val params: WorkerParameters,
    private val repository: Repository,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        startForegroundService()

        val zipFile = params.inputData.getString(WorkerKeys.ZIP_URI)
            ?.toUri()?.toFile()

        if (zipFile != null) {
            return@withContext try {
                val dirList = unzip(zipFile, appContext.filesDir.toString())
                zipFile.delete()
                repository.saveIntoDatabase(dirList[0])
                Result.success()
            } catch (e: IOException) {
                Result.failure(
                    workDataOf(
                        WorkerKeys.ERROR_MSG to e.message
                    )
                )
            }
        }
        Result.failure()
    }

    private suspend fun startForegroundService() {
        setForeground(
            ForegroundInfo(
                1,
                with(applicationContext) {
                    processNotificationBuilder(
                        this,
                        getString(R.string.notification_channel_id),
                        getString(R.string.unzip_notification_title),
                        getString(R.string.unzip_notification_text),
                        R.drawable.ic_launcher_foreground
                    )
                }
            )
        )
    }
}

package ru.flobsterable.flashCards.workers

import android.content.Context
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.flobsterable.flashCards.R
import ru.flobsterable.flashCards.notification.createNotificationChannel
import ru.flobsterable.flashCards.notification.processNotificationBuilder
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL

private const val TEMP_FILE = "temp.zip"
private const val URL = "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/B9NXidpS2lSmTQ"

class LoaderWorker(
    context: Context,
    workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        with(applicationContext) {
            createNotificationChannel(
                this,
                getString(R.string.notification_channel_id),
                getString(R.string.notification_channel_name),
            )
        }

        startForegroundService()
        val file = File(applicationContext.cacheDir, TEMP_FILE)
        val url = URL(URL)
        downloadFile(url, file)
    }

    private fun downloadFile(url: URL, file: File): Result {
        return try {
            url.openStream().use { input ->
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                }
            }
            Result.success(
                workDataOf(
                    WorkerKeys.ZIP_URI to file.toUri().toString()
                )
            )
        } catch (e: IOException) {
            Result.failure(
                workDataOf(
                    WorkerKeys.ERROR_MSG to file.toUri().toString()
                )
            )
        }
    }

    private suspend fun startForegroundService() {
        setForeground(
            ForegroundInfo(
                1,
                with(applicationContext) {
                    processNotificationBuilder(
                        this,
                        getString(R.string.notification_channel_id),
                        getString(R.string.download_notification_title),
                        getString(R.string.download_notification_text),
                        R.drawable.ic_launcher_foreground
                    )
                }
            )
        )
    }
}

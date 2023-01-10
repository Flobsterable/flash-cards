package ru.flobsterable.flashCards.workers

import android.content.Context
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.flobsterable.flashCards.R
import ru.flobsterable.flashCards.notification.processNotificationBuilder
import java.io.BufferedInputStream
import java.io.File
import java.io.IOException
import java.util.zip.ZipFile

private const val BUFFER_SIZE = 1024

class UnzipWorker(private val appContext: Context, private val params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        startForegroundService()

        val zipFile = params.inputData.getString(WorkerKeys.ZIP_URI)
            ?.toUri()?.toFile()

        if (zipFile != null) {
            return@withContext try {
                unzip(zipFile, appContext.filesDir.toString())
                zipFile.delete()
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

    private fun unzip(zipFile: File, targetPath: String) {
        val zip = ZipFile(zipFile, 1)
        val enumeration = zip.entries()
        while (enumeration.hasMoreElements()) {
            val entry = enumeration.nextElement()
            val destFilePath = File(targetPath, entry.name)
            destFilePath.parentFile?.mkdirs()

            if (entry.isDirectory) {
                continue
            }

            val bufferedIs = BufferedInputStream(zip.getInputStream(entry))

            bufferedIs.use {
                destFilePath.outputStream().buffered(BUFFER_SIZE).use { bos ->
                    bufferedIs.copyTo(bos)
                }
            }
        }
    }
}

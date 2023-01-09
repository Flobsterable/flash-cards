package ru.flobsterable.flashCards.workers

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
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
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.zip.ZipFile

private const val BUFFER_SIZE = 4096

class UnzipWorker(private val appContext: Context, private val params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        startForegroundService()

        val zipFile = params.inputData.getString(WorkerKeys.ZIP_URI)
            ?.toUri()?.toFile()

        if (zipFile != null) {
            try {
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

    @Throws(IOException::class)
    private fun unzip(zipFilePath: File, destDirectory: String) {
        File(destDirectory).run {
            if (!exists()) {
                mkdirs()
            }
        }
        ZipFile(zipFilePath).use { zip ->

            zip.entries().asSequence().forEach { entry ->

                zip.getInputStream(entry).use { input ->

                    val filePath = destDirectory + File.separator + entry.name

                    if (!entry.isDirectory) {
                        // if the entry is a file, extracts it
                        extractFile(input, filePath)
                    } else {
                        // if the entry is a directory, make the directory
                        val dir = File(filePath)
                        dir.mkdir()
                    }
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun extractFile(inputStream: InputStream, destFilePath: String) {
        val bos = BufferedOutputStream(FileOutputStream(destFilePath))
        val bytesIn = ByteArray(BUFFER_SIZE)
        var read: Int
        while (inputStream.read(bytesIn).also { read = it } != -1) {
            bos.write(bytesIn, 0, read)
        }
        bos.close()
    }
}

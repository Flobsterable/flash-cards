package ru.flobsterable.flashCards.workers

import android.content.Context
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.flobsterable.flashCards.R
import ru.flobsterable.flashCards.data.findFileByExtension
import ru.flobsterable.flashCards.data.repository.parser.models.Deck
import ru.flobsterable.flashCards.data.unzip
import ru.flobsterable.flashCards.notification.processNotificationBuilder
import java.io.IOException

private const val JSON_EXTENSION = "json"

class UnzipWorker(private val appContext: Context, private val params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    @OptIn(ExperimentalStdlibApi::class)
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        startForegroundService()

        val zipFile = params.inputData.getString(WorkerKeys.ZIP_URI)
            ?.toUri()?.toFile()

        if (zipFile != null) {
            return@withContext try {
                unzip(zipFile, appContext.filesDir.toString())
                zipFile.delete()
                val jsonFile = findFileByExtension(appContext.filesDir, JSON_EXTENSION)
                val json = jsonFile?.inputStream()?.bufferedReader().use { it!!.readText() }
                val moshi: Moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter<Deck>()
                val obj = jsonAdapter.fromJson(json)

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

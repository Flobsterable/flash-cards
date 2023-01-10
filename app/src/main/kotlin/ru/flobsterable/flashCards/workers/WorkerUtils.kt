package ru.flobsterable.flashCards.workers

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

const val WORK_ID = "download"

class Worker(appContext: Context) {

    val workManager = WorkManager.getInstance(appContext)

    val downloadRequest = OneTimeWorkRequestBuilder<LoaderWorker>()
        .setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        ).build()

    val unzipRequest = OneTimeWorkRequestBuilder<UnzipWorker>().build()

    fun downloadFile() {
        workManager.beginUniqueWork(
            WORK_ID,
            ExistingWorkPolicy.KEEP,
            downloadRequest
        )
            .then(unzipRequest)
            .enqueue()
    }

    fun cancelDownloadingFile() {
        workManager.cancelUniqueWork(WORK_ID)
    }
}

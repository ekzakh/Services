package com.ekzak.services

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class MyWorker(
    context: Context,
    private val workerParameters: WorkerParameters,
) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        val page = workerParameters.inputData.getInt(PAGE, 0)
        for (i in 0 until 10) {
            Thread.sleep(1000)
            log("Timer: $i page: $page")
        }
        return Result.success()
    }

    private fun log(msg: String) {
        Log.d("SERVICE_TAG", "MyWorker $msg")
    }

    companion object {
        const val WORK_NAME = "my_work"
        private const val PAGE = "page"

        fun makeRequest(page: Int): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<MyWorker>()
                .setInputData(workDataOf(PAGE to page))
                .setConstraints(makeConstraints())
                .build()
        }

        private fun makeConstraints(): Constraints {
            return Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build()
        }
    }
}

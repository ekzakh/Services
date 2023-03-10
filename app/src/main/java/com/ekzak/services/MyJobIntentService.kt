package com.ekzak.services

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class MyJobIntentService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        val page = intent.getIntExtra(PAGE, 0)
        for (i in 0 until 10) {
            Thread.sleep(1000)
            log("Timer: $i page: $page")
        }
    }

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(msg: String) {
        Log.d("SERVICE_TAG", "MyIntentService $msg")
    }

    companion object {
        private const val JOB_ID = 515
        private const val PAGE = "page"

        fun enqueue(context: Context, page: Int) {
            enqueueWork(
                context,
                MyJobIntentService::class.java,
                JOB_ID,
                newIntent(context, page)
            )
        }

        private fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyJobIntentService::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}

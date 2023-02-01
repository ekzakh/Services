package com.ekzak.services

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyIntentService : IntentService(NAME) {
    override fun onCreate() {
        super.onCreate()
        setIntentRedelivery(true)
        log("onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onHandleIntent(intent: Intent?) {
        for (i in 0 until 10) {
            Thread.sleep(1000)
            log("Timer: $i")
        }
    }

    private fun log(msg: String) {
        Log.d("SERVICE_TAG", "MyIntentService $msg")
    }

    companion object {
        private const val NAME = "my_intent_service"
        fun newIntent(context: Context): Intent {
            return Intent(context, MyIntentService::class.java)
        }
    }
}

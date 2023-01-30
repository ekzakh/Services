package com.ekzak.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyForegroundService : Service() {

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        log("onCreate")

        val myNotification =
            SimpleNotification(this).createNotification("Foreground notification", "Service is working")
        startForeground(NORIFICATION_ID, myNotification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand")
        scope.launch {
            for (i in 0 until 10) {
                delay(1000)
                log("Timer: $i")
            }
            stopSelf()
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        scope.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun log(msg: String) {
        Log.d("SERVICE_TAG", "MyForegroundService $msg")
    }

    companion object {
        private const val NORIFICATION_ID = 1
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"

        fun newIntent(context: Context): Intent {
            return Intent(context, MyForegroundService::class.java)
        }
    }
}

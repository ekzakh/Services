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

class MyService : Service() {

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand")
        val start = intent?.getIntExtra(EXTRA, 0) ?: 0
        scope.launch {
            for (i in start until 100) {
                delay(1000)
                log("Timer: $i")
            }
        }
        return START_STICKY
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
        Log.d("SERVICE_TAG", "MyService $msg")
    }

    companion object {
        private const val EXTRA = "extra"
        fun newIntent(context: Context): Intent {
            return Intent(context, MyService::class.java).apply {
                putExtra(EXTRA, 30)
            }
        }
    }
}

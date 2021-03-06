package com.android.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyForegroundService:Service() {
    private val scope= CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        log("Oncreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand")
        val start=intent?.getIntExtra(EXTRA_INT,0)
        scope.launch {

            if (start != null) {
                for (i in start until start+100) {
                    delay(1000)
                    log("Timer: $i") }
            }

        }
        return Service.START_REDELIVER_INTENT

    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        scope.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }


    private fun log(message: String) {
        Log.d("ServiceTag", "ServiceTag:  $message")


    }
    companion object{
        const val EXTRA_INT="key"
        fun newIntent(context: Context,start:Int):Intent{
            return Intent(context,MyService::class.java).apply {
                putExtra(EXTRA_INT,start)
            }

        }
    }
}



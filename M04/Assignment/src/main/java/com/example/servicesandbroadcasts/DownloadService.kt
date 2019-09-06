package com.example.servicesandbroadcasts

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class DownloadService: Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.i("DownloadService", "Started")

        Thread(Runnable(){
            val bitmap = NetworkAdapter.getBitmapFromUrl("http://images.clipartpanda.com/apple-20clip-20art-nicubunu_Apple_Clipart_Free.png")
            val i = Intent(FILE_DOWNLOAD_ACTION).apply{
                putExtra(IMAGE_DOWNLOADED, bitmap)
            }
            LocalBroadcastManager.getInstance(this).sendBroadcast(i)
        }).start()
        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        throw(UnsupportedOperationException())
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("DownloadService", "Destroyed")
    }

    companion object{
        const val IMAGE_DOWNLOADED = "IMAGE_DOWNLOADED"
        const val FILE_DOWNLOAD_ACTION = "android.intent.action.DOWNLOAD"
    }
}
package com.example.serviceandbroadcasts

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.lang.UnsupportedOperationException

class Service: Service(){

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("LargeImageDownload", "started")


        Thread(Runnable{
            val width = intent?.getIntExtra(BITMAP_WIDTH, 0) ?: 0
            val height = intent?.getIntExtra(BITMAP_HEIGHT, 0) ?: 0
            val bitmap = NetworkAdapter.getBitmapFromUrl("https://i.imgur.com/HaSmgGn.jpg", width, height)

            val intent = Intent(FILE_DOWNLOADED_ACTION).apply{
                putExtra(DOWNLOADED_IMAGE, bitmap)
            }

            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            stopSelf()
        }).start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        Log.i("LargeImageDownload", "created")
        super.onCreate()
    }

    override fun onDestroy() {
        Log.i("LargeImageDownload", "destroyed")
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        throw UnsupportedOperationException()
    }
    companion object{
        const val FILE_DOWNLOADED_ACTION = "com.lambdaschool.serviceimagedownloader.FILE_DOWNLOADED"
        const val DOWNLOADED_IMAGE = "downloadedImage"
        const val BITMAP_HEIGHT = 150.toString()
        const val BITMAP_WIDTH = 100.toString()
    }
}
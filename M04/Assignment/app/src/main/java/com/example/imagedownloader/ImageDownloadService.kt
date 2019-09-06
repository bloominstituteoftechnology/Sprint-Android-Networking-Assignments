package com.example.imagedownloader

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class ImageDownloadService: Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("image download","started")
        Thread(Runnable() {
            val width = intent?.getIntExtra(BITMAP_WIDTH, 0) ?: 0
            val height = intent?.getIntExtra(BITMAP_HEIGHT, 0) ?: 0
            val bitmap = NetworkAdapter.getBitmapFromUrl("https://i.imgur.com/HaSmgGn.jpg", width, height)
            val intent = Intent(FILE_DOWNLOADED_ACTION).apply {
                putExtra(DOWNLOADED_IMAGE, bitmap)
            }
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            stopSelf()
        }).start()
        return super.onStartCommand(intent, flags, startId)

    }
    override fun onCreate() {
        Log.i("image download","created")
        super.onCreate()
    }
    override fun onDestroy() {
        Log.i("image download","destroyed")
        super.onDestroy()
    }
    override fun onBind(p0: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    companion object{
        const val FILE_DOWNLOADED_ACTION = "com.example.ImageDownloadService.FILE_DOWNLOADED"
        const val DOWNLOADED_IMAGE= "downloaded image"
        const val BITMAP_WIDTH= "bmw"
        const val BITMAP_HEIGHT= "bmh"
    }

}
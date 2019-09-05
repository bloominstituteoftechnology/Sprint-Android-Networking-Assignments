package com.example.imagedownloader

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

lateinit var imageDownloadReceiver:BroadcastReceiver
    lateinit var intentFilter:IntentFilter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_download_image.setOnClickListener {
            val serviceIntent= Intent(this,ImageDownloadService::class.java)
            serviceIntent.putExtra(ImageDownloadService.BITMAP_WIDTH, downloaded_image.width)
            serviceIntent.putExtra(ImageDownloadService.BITMAP_HEIGHT, downloaded_image.height)
            this.startService(serviceIntent)

        }
        imageDownloadReceiver=object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                if(intent?.action==ImageDownloadService.FILE_DOWNLOADED_ACTION){
                    val bitmap =intent.getParcelableExtra<Bitmap>(ImageDownloadService.DOWNLOADED_IMAGE)
                    downloaded_image.setImageBitmap(bitmap)
                }
            }
        }
         intentFilter=IntentFilter().apply{
            addAction(ImageDownloadService.FILE_DOWNLOADED_ACTION)
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(imageDownloadReceiver,intentFilter)



    }

    override fun onDestroy() {
        unregisterReceiver(imageDownloadReceiver)
        super.onDestroy()
    }

    override fun onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(imageDownloadReceiver,intentFilter)
        super.onResume()
    }

    override fun onPause() {
        unregisterReceiver(imageDownloadReceiver)
        super.onPause()
    }
}

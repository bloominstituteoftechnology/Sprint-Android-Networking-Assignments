package com.example.imagedownloader

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //set up a member var to hold broadcast receiver
    private lateinit var imageDownloadReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageDownloadReceiver = object: BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                // guard against an intent doest have the right action
                if (intent?.action == LargeImageDownloadService.FILE_DOWNLOADED_ACTION) {
                    //gets the putExtra
                    val bitmap =
                        intent.getParcelableExtra<Bitmap>(LargeImageDownloadService.DOWNLOADED_IMAGE)
                    imageView.setImageBitmap(bitmap)
                }
            }
        }
        myImageDownload.setOnClickListener {
            // TODO: S04M04-4 Start service
            val serviceIntent = Intent(this, LargeImageDownloadService::class.java)
            serviceIntent.putExtra(LargeImageDownloadService.BITMAP_WIDTH, imageView.width)
            serviceIntent.putExtra(LargeImageDownloadService.BITMAP_HEIGHT, imageView.height)
            this.startService(serviceIntent)
            //this way it will be desabled
            myImageDownload.isEnabled = false

        }


    }
}

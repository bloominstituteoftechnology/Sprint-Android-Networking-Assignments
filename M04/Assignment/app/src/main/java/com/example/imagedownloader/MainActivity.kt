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

class MainActivity : AppCompatActivity() {
    //set up a member var to hold broadcast receiver
    private lateinit var imageDownloadReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageDownloadReceiver = object : BroadcastReceiver() {
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
        // TODO: S04M04-6 Add IntentFilter
        //scopes intents
        val intentFilter = IntentFilter().apply {
            addAction(LargeImageDownloadService.FILE_DOWNLOADED_ACTION)
        }
        // TODO: S04M04-7 Register receiver
        //register using that filter
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(imageDownloadReceiver, intentFilter)
    }
        override fun onDestroy() {
            super.onDestroy()
            // TODO S04M04-10 Unregister receivers
            unregisterReceiver(imageDownloadReceiver)
        }

    }


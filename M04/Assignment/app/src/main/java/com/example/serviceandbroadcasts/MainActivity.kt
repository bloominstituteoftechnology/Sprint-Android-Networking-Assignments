package com.example.serviceandbroadcasts

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

    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_download.setOnClickListener {
            var serviceIntent = Intent(this, Service::class.java)
            serviceIntent.putExtra(Service.BITMAP_WIDTH, image_view.width)
            serviceIntent.putExtra(Service.BITMAP_HEIGHT, image_view.height)
            this.startService(serviceIntent)
        }

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                if (intent?.action == Service.FILE_DOWNLOADED_ACTION) {
                    val bitmap = intent.getParcelableExtra<Bitmap>(Service.DOWNLOADED_IMAGE)
                    image_view.setImageBitmap(bitmap)
                }
            }
        }

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == Service.FILE_DOWNLOADED_ACTION){
                    val bitmap = intent.getParcelableExtra<Bitmap>(Service.DOWNLOADED_IMAGE)
                    image_view.setImageBitmap(bitmap)
                }

            }
        }
    }

        override fun onResume(){
            val intentFilter = IntentFilter().apply {
                addAction(Service.FILE_DOWNLOADED_ACTION)
            }
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter)
            super.onResume()
        }

    override fun onPause(){
        unregisterReceiver((broadcastReceiver))
        super.onPause()
    }
    }


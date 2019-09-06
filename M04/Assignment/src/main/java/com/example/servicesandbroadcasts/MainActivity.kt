package com.example.servicesandbroadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val br = MyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        download_button.setOnClickListener {
            val serviceIntent = Intent(this, DownloadService::class.java)
            startActivity(serviceIntent)
        }

        registerReceiver(br, IntentFilter(DownloadService.FILE_DOWNLOAD_ACTION))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }

    private inner class MyBroadcastReceiver : BroadcastReceiver(){

        override fun onReceive(context: Context?, i: Intent?) {
            i?.let{
                Log.i("$this.javaClass.toString()", "SUCCESSFULLY RECEIVED")

                if(i.action == DownloadService.FILE_DOWNLOAD_ACTION){
                    downloaded_image.setImageBitmap(i.getParcelableExtra(DownloadService.IMAGE_DOWNLOADED))
                }
            }
        }
    }
}

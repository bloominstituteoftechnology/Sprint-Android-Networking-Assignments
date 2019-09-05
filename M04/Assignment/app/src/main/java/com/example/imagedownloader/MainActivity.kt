package com.example.imagedownloader

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //set up a member var to hold broadcast receiver
    private lateinit var imageDownloadReceiver: BroadcastReceiver

    //TODO set up download manager
    private lateinit var downloadManager: DownloadManager

    // setting it up here so we can register or unregister for download receiver
    private lateinit var downloadReceiver: BroadcastReceiver

    //TODO fix the enque
    var enqueue: Long = 0

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
        // TODO set up download manager (b)
        downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        imageDownload.setOnClickListener {
            //set up a request to enque (take the url and parse through Uri)
            val request = DownloadManager.Request(Uri.parse("https://i.imgur.com/HaSmgGn.jpg"))

            enqueue = downloadManager.enqueue(request)
        }
        // TODO set up receiver to tell when download is done
        val downloadIntentFilter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)

        downloadReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent?.action
                when (action) {
                    DownloadManager.ACTION_DOWNLOAD_COMPLETE -> {
                        val query = DownloadManager.Query()
                        query.setFilterById(enqueue)
                        val c = downloadManager.query(query)
                        if (c.moveToFirst()) {
                            val columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS)
                            if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
                                val uriString = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
                                val uriFileDescriptor = contentResolver.openFileDescriptor(Uri.parse(uriString), "r")
                                val width = imageView.width
                                val height = imageView.height
                                imageView.setImageBitmap(uriFileDescriptor?.let {
                                    decodeSampledBitmapFileDescriptor(
                                        it, width, height)
                                })
                            }
                        }
                    }
                    DownloadManager.ACTION_NOTIFICATION_CLICKED -> {
                    }
                    DownloadManager.ACTION_VIEW_DOWNLOADS -> {
                    }
                }
            }
        }

        // then register the receiver
        registerReceiver(downloadReceiver, downloadIntentFilter)

        // TODO to see if the download has actually occured

        showDownloads.setOnClickListener {
            val i = Intent()
            i.action = DownloadManager.ACTION_VIEW_DOWNLOADS
            startActivity(i)
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


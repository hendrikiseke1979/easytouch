package de.otto.ov.brain.easytouch

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.util.Log
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class TakePictureActivity : AppCompatActivity() {

    private var takePictureButton: Button? = null
    private var imageView: ImageView? = null
    private var file: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_picture)

        checkPermissions()
    }

    /**
     * disable picture button if permissions are not granted
     */
    private fun checkPermissions() {
        takePictureButton = this.findViewById(R.id.button_take_image)
        imageView = findViewById<View>(R.id.imageview) as ImageView

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            takePictureButton!!.isEnabled = false
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        }
    }

}

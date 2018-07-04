package de.otto.ov.brain.easytouch

import android.hardware.Camera
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageButton

class TakePictureActivity : AppCompatActivity() {

    private var mCamera: Camera? = null
    private var mCameraView: CameraView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_picture)
        try {
            mCamera = Camera.open()//you can use open(int) to use different cameras
        } catch (e: Exception) {
            Log.d("ERROR", "Failed to get camera: " + e.message)
        }


        if (mCamera != null) {
            mCameraView = CameraView(this, mCamera)//create a SurfaceView to show camera data
            val cameraView = findViewById<FrameLayout>(R.id.camera_view)
            cameraView.addView(mCameraView)//add the SurfaceView to the layout
        }

        //btn to close the application
        val imgClose = findViewById<ImageButton>(R.id.imgClose)
        imgClose.setOnClickListener { System.exit(0) }
    }

}

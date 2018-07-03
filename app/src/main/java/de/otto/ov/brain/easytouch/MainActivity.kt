package de.otto.ov.brain.easytouch

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.os.StrictMode
import android.app.Activity
import android.net.ConnectivityManager
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        setContentView(R.layout.activity_main)
    }

    fun isConnected(): Boolean {
        val connMgr = getSystemService(Activity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun buttonClickScale(view: View) {
        if(isConnected()) {
            startActivity(Intent(this, ScaleActivity::class.java))
        } else {
            Toast.makeText(applicationContext, R.string.error_no_network, Toast.LENGTH_SHORT).show()
        }
    }
}

package de.otto.ov.brain.easytouch

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

abstract class AbstractActivity : AppCompatActivity() {
    abstract protected val jsonKey: String
    abstract protected val label: Int;

    protected fun getValueFromJson(json: String): String {
        val jsonObject = JSONObject(json)
        val dataObject = (jsonObject.get("data") as JSONArray).get(0)

        return (dataObject as JSONObject).get(jsonKey).toString()
    }

    protected inner class HttpAsyncTask(val id: Int, val url: String) : AsyncTask<String, Void, String>() {
        private var spinner: ProgressBar = findViewById(R.id.progressBar);

        override fun doInBackground(vararg url: String): String {
            return URL(this.url).readText()
        }

        // onPostExecute displays the results of the AsyncTask.
        override fun onPostExecute(result: String) {
            spinner.visibility = View.INVISIBLE;
            findViewById<TextView>(id).apply {
                text = applicationContext.getString(label, getValueFromJson(result))
            }
        }
    }
}
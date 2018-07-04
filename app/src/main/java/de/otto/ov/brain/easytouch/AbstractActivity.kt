package de.otto.ov.brain.easytouch

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

abstract class AbstractActivity : AppCompatActivity() {
    protected abstract val contentView: Int
    protected abstract val jsonKey: String
    protected abstract val label: Int
    protected abstract var taskDefinitions: HashMap<Int, String>


    private var tasks = ArrayList<HttpAsyncTask>()

    protected fun getValueFromJson(json: String): String {
        val jsonObject = JSONObject(json)
        val dataObject = (jsonObject.get("data") as JSONArray).get(0)

        return (dataObject as JSONObject).get(jsonKey).toString()
    }

    protected inner class HttpAsyncTask(private val id: Int, private val url: String) : AsyncTask<String, Void, String>() {
        private var spinner: ProgressBar = findViewById(R.id.progressBar)

        override fun doInBackground(vararg url: String): String {
            return URL(this.url).readText()
        }

        // onPostExecute displays the results of the AsyncTask.
        override fun onPostExecute(result: String) {
            tasks.remove(this)
            if(tasks.size == 0) {
                spinner.visibility = View.INVISIBLE
            }
            findViewById<TextView>(id).apply {
                text = applicationContext.getString(label, getValueFromJson(result))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)

        try {
            for ((id, url) in taskDefinitions) {
                val task = HttpAsyncTask(id, url)
                task.execute()
                tasks.add(task)
            }
        } catch (e: Exception) {
            Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        for (task in tasks) {
            task.cancel(true)
        }
    }
}
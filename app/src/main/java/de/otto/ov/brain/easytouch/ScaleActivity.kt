package de.otto.ov.brain.easytouch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class ScaleActivity : AppCompatActivity() {
    fun getValueFromJson(url: String): String {
        val jsonObject = JSONObject(URL(url).readText());
        val dataObject = (jsonObject.get("data") as JSONArray).get(0);

        return (dataObject as JSONObject).get("Gewicht_kg").toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale)

        val textView = findViewById<TextView>(R.id.lightest).apply {
            text = getValueFromJson("http://tavira-net.servebeer.com/storage/E2C_24.json") + " kg"
        }
        val textView2 = findViewById<TextView>(R.id.heaviest).apply {
            text = getValueFromJson("http://tavira-net.servebeer.com/storage/E2C_23.json") + " kg"
        }
    }
}

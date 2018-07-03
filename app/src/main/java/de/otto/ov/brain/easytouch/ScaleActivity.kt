package de.otto.ov.brain.easytouch

import android.os.Bundle
import android.widget.Toast

class ScaleActivity : AbstractActivity() {
    override val jsonKey = "Gewicht_kg"
    override val label = R.string.basket_weight_label;

    private lateinit var taskLighter: HttpAsyncTask
    private lateinit var taskHeavier: HttpAsyncTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale)

        try {
            taskHeavier = HttpAsyncTask(R.id.heaviest, "http://tavira-net.servebeer.com/storage/E2C_23.json")
            taskHeavier.execute()

            taskLighter = HttpAsyncTask(R.id.lightest, "http://tavira-net.servebeer.com/storage/E2C_24.json")
            taskLighter.execute()
        } catch(e: Exception) {
            Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        taskHeavier.cancel(true)
        taskLighter.cancel(true)

    }
}

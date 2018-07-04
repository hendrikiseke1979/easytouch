package de.otto.ov.brain.easytouch

class ScaleActivity : AbstractActivity() {
    override val contentView = R.layout.activity_scale
    override val jsonKey = "Gewicht_kg"
    override val label = R.string.basket_weight_label;
    override var taskDefinitions: HashMap<Int, String> = hashMapOf(
            R.id.heaviest to "https://raw.githubusercontent.com/hiseke/easytouch/master/app/assets/E2C_23.json",
            R.id.lightest to "https://raw.githubusercontent.com/hiseke/easytouch/master/app/assets/E2C_24.json"
    )
}

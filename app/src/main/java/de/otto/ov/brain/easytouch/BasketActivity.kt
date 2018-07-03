package de.otto.ov.brain.easytouch

class BasketActivity : AbstractActivity() {
    override val contentView = R.layout.activity_basket
    override val jsonKey = "Nachfrage"
    override val label = R.string.basket_size_label;
    override var taskDefinitions: HashMap<Int, String> = hashMapOf(
            R.id.heaviest to "http://tavira-net.servebeer.com/storage/E2C_20.json",
            R.id.lightest to "http://tavira-net.servebeer.com/storage/E2C_21.json"
    )
}

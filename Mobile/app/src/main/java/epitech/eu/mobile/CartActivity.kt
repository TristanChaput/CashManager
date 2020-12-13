package epitech.eu.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Activity that shows a list of added products, payment button and the bill
 */
class CartActivity : AppCompatActivity(), ArticleListener, View.OnClickListener {
    private lateinit var cartList: ArrayList<Article>

    /**
     * On create function and set the activity with data.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartList = intent.getParcelableArrayListExtra(Tools.ARRAY_INTENT_PARCELABLE)!!

        val recyclerView = findViewById<RecyclerView>(R.id.cartRecyclerView)
        val textViewBill = findViewById<TextView>(R.id.textViewBill)
        val buttonBack = findViewById<Button>(R.id.buttonBack)
        val buttonPayment =  findViewById<Button>(R.id.buttonPayment)
        buttonBack.setOnClickListener(this)
        buttonPayment.setOnClickListener(this)
        textViewBill.text = Tools.computeBill(cartList)
        recyclerView.adapter = CartAdapter(cartList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }
    /**
     *  Call a [ListenerType] that execute the function corresponding to a specific event.
     *  [ListenerType.BackToArticlesOnClickButtonListener] conduct to the products list and give the cart list.
     *  [ListenerType.PaymentOnClickButtonListener] conduct to the payment and give the amount of bill.
     *  @param [clicked] used to find the current listener
     */
    override fun articleEvent(clicked: ListenerType) {
        when (clicked) {
            is ListenerType.BackToArticlesOnClickButtonListener -> {
                val intent = Intent(this, ArticleActivity::class.java)
                intent.putExtra(Tools.ARRAY_INTENT_PARCELABLE, cartList)
                startActivity(intent)
            }
            is ListenerType.PaymentOnClickButtonListener -> {
                val intent = Intent(this, PaymentActivity::class.java)
                intent.putExtra(Tools.ARRAY_INTENT_PARCELABLE, Tools.computeBill(cartList))
                startActivity(intent)
            }
            else -> {

            }
        }
    }

    /**
     * Call event on button click in a view
     * @param [v] the current view
     */
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.buttonBack -> {
                this.articleEvent(ListenerType.BackToArticlesOnClickButtonListener)
            }
            R.id.buttonPayment -> {
                this.articleEvent(ListenerType.PaymentOnClickButtonListener)
            }
        }
    }
}
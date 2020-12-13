package epitech.eu.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity(), ArticleListener, View.OnClickListener {
    private lateinit var cartList: ArrayList<Article>

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
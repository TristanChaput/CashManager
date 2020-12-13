
package epitech.eu.mobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

/**
 * Activity that shows the detail of a product, cart button, back button and the bill.
 */
class DetailActivity : AppCompatActivity(), ArticleListener, View.OnClickListener {
    private lateinit var textViewCount: TextView
    private lateinit var textViewBill: TextView
    private lateinit var article: Article
    private lateinit var cartList: ArrayList<Article>

    /**
     * On create function and set the activity with data.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val textViewName = findViewById<TextView>(R.id.textViewName)
        val textViewPrice = findViewById<TextView>(R.id.textViewPrice)
        val textViewDescription = findViewById<TextView>(R.id.textViewDescription)
        val buttonAdd = findViewById<Button>(R.id.buttonAddToCart)
        val buttonBack = findViewById<Button>(R.id.buttonBack)
        val buttonCart = findViewById<Button>(R.id.buttonCart)
        textViewBill = findViewById(R.id.textViewBill)
        textViewCount = findViewById(R.id.textViewCount)

        article = intent.getParcelableExtra(Tools.INTENT_PARCELABLE)!!
        cartList = intent.getParcelableArrayListExtra(Tools.ARRAY_INTENT_PARCELABLE)!!
        textViewCount.text = Tools.countItemInCart(cartList, article)
        textViewBill.text = Tools.computeBill(cartList)

        Picasso.get().load(article.img).into(imageView)
        textViewName.text = article.name
        textViewPrice.text = article.prix.toString().plus("€")
        textViewDescription.text = article.description

        buttonAdd.setOnClickListener(this)
        buttonBack.setOnClickListener(this)
        buttonCart.setOnClickListener(this)
    }

    /**
     *  Call a [ListenerType] that execute the function corresponding to a specific event.
     *  [ListenerType.AddOnClickButtonListener] add a product to cart list, display the number of items and display the bill.
     *  [ListenerType.BackToArticlesOnClickButtonListener] conduct to the products list and give the cart list.
     *  [ListenerType.CartOnClickButtonListener] conduct to the cart and give the cart list.
     *  @param [clicked] used to find the current listener
     */
    override fun articleEvent(clicked: ListenerType) {
        when (clicked) {
            is ListenerType.AddOnClickButtonListener -> {
                cartList.add(article)
                textViewCount.text = Tools.countItemInCart(cartList, article)
                textViewBill.text = Tools.computeBill(cartList)
            }
            is ListenerType.BackToArticlesOnClickButtonListener -> {
                val intent = Intent(this, ArticleActivity::class.java)
                intent.putExtra(Tools.ARRAY_INTENT_PARCELABLE, cartList)
                startActivity(intent)
            }
            is ListenerType.CartOnClickButtonListener -> {
                val intent = Intent(this, CartActivity::class.java)
                intent.putExtra(Tools.ARRAY_INTENT_PARCELABLE, cartList)
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
            R.id.buttonAddToCart -> {
                this.articleEvent(ListenerType.AddOnClickButtonListener)
            }
            R.id.buttonBack -> {
                this.articleEvent(ListenerType.BackToArticlesOnClickButtonListener)
            }
            R.id.buttonCart -> {
                this.articleEvent(ListenerType.CartOnClickButtonListener)
            }
        }
    }
}


package epitech.eu.mobile

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.FileUtils.copy
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import java.io.*
import java.net.URL


class DetailActivity : AppCompatActivity(), ArticleListener, View.OnClickListener {
    private lateinit var textViewCount: TextView
    private lateinit var textViewBill: TextView
    private lateinit var article: Article
    private lateinit var cartList: ArrayList<Article>

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


        imageView.setImageResource(article.img)
        textViewName.text = article.name
        textViewPrice.text = article.prix.toString().plus("€")
        textViewDescription.text = article.description

        buttonAdd.setOnClickListener(this)
        buttonBack.setOnClickListener(this)
        buttonCart.setOnClickListener(this)
    }

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

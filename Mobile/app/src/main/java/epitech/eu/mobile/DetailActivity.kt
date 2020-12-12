package epitech.eu.mobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity(), ArticleListener, View.OnClickListener {
    private lateinit var textViewCount: TextView
    private lateinit var textViewBill: TextView
    private lateinit var article: Article
    private lateinit var cartList: ArrayList<Article>
    private var bill = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        article = intent.getParcelableExtra(Tools.INTENT_PARCELABLE)!!
        cartList = intent.getParcelableArrayListExtra(Tools.ARRAY_INTENT_PARCELABLE)!!
        bill = intent.getDoubleExtra(Tools.BILL_INTENT_PARCELABLE, 0.00)
        var countItem = 0
        val imageview = findViewById<ImageView>(R.id.imageView)
        val texttitle = findViewById<TextView>(R.id.textViewTitle)
        val textprice = findViewById<TextView>(R.id.textViewPrice)
        val textdescription = findViewById<TextView>(R.id.textViewDescription)
        val buttonAdd = findViewById<Button>(R.id.buttonAddToCart)
        val buttonBack = findViewById<Button>(R.id.buttonBack)
        textViewBill = findViewById(R.id.textViewBill)
        textViewCount = findViewById(R.id.textViewCount)

        if (!cartList.isNullOrEmpty())
            for (anArticle in cartList)
                if (article.id == anArticle.id) countItem++
        imageview.setImageResource(article.img)
        texttitle.text = article.name
        textprice.text = article.prix.toString()
        textdescription.text = article.description
        textViewCount.text =
            if (countItem > 1) "$countItem items"
            else               "$countItem item"
        textViewBill.text = Tools.roundInEuro(bill)
        buttonAdd.setOnClickListener(this)
        buttonBack.setOnClickListener(this)
    }

    override fun articleEvent(clicked: ListenerType) {
        when (clicked) {
            is ListenerType.AddOnClickButtonListener -> {
                var countItem = 0
                cartList.add(article)
                for (anArticle in cartList)
                    if (article.id == anArticle.id)
                        countItem++
                textViewCount.text =
                    if (countItem > 1) "$countItem items"
                    else               "$countItem item"
            }
            is ListenerType.UpdateBillOnClickButtonListener -> {
                bill = 0.00
                for (anArticle in cartList)
                    bill += anArticle.prix
                textViewBill.text = Tools.roundInEuro(bill)
            }
            is ListenerType.BackToArticlesOnClickButtonListener -> {
                val intent = Intent(this, ArticleActivity::class.java)
                intent.putExtra(Tools.BILL_INTENT_PARCELABLE, bill)
                intent.putParcelableArrayListExtra(Tools.ARRAY_INTENT_PARCELABLE, cartList)
                startActivity(intent)
            }
            else -> {}
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.buttonAddToCart -> {
                this.articleEvent(ListenerType.AddOnClickButtonListener)
                this.articleEvent(ListenerType.UpdateBillOnClickButtonListener)
            }
            R.id.buttonBack -> {
                this.articleEvent(ListenerType.BackToArticlesOnClickButtonListener)
            }
        }
    }
}

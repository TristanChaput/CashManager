package epitech.eu.mobile

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ArticleActivity : AppCompatActivity(), ArticleListener, View.OnClickListener {
    private val articleList = generateArticleList()
    private var cartList = Cart()
    private lateinit var textViewBill: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        val token = intent.getStringExtra("token")
        val network = intent.getStringExtra("network")

        println(intent.getStringExtra("network"))
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_articles)
        val buttonCart = findViewById<Button>(R.id.buttonCart)
        buttonCart.setOnClickListener(this)
        textViewBill = findViewById(R.id.textView)
        textViewBill.text = cartList.computeBill()
        recyclerView.adapter = ArticleAdapter(articleList, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        if (intent.getParcelableExtra<Parcelable>(Tools.ARRAY_INTENT_PARCELABLE) != null)
            cartList = intent.getParcelableExtra(Tools.ARRAY_INTENT_PARCELABLE)!!
    }

    private fun generateArticleList(): ArrayList<Article> {
        val listArticle = Cart()

        //A COMPLETER AVEC L'API
        listArticle.add(Article("1", R.drawable.croisiere, "Croisiere maldives", "", 4999.99))
        listArticle.add(Article("2", R.drawable.croisiere, "Croisiere costa rica", "", 6299.99))
        listArticle.add(Article("3", R.drawable.croisiere, "Croisiere iles canaries", "", 3999.99))
        listArticle.add(Article("4", R.drawable.croisiere, "Croisiere grece", "", 7899.99))
        listArticle.add(Article("5", R.drawable.croisiere, "Croisiere grece bis", "une description",7899.99))

        return listArticle
    }

    override fun articleEvent(clicked: ListenerType) {
        when (clicked) {
            is ListenerType.OnArticleClickListener -> {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(Tools.INTENT_PARCELABLE, articleList[clicked.position])
                intent.putExtra(Tools.ARRAY_INTENT_PARCELABLE, cartList)
                startActivity(intent)
            }
            is ListenerType.CartOnClickButtonListener -> {
                val intent = Intent(this, CartActivity::class.java)
                intent.putExtra(Tools.ARRAY_INTENT_PARCELABLE, cartList)
                startActivity(intent)
            }
            else -> {}
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.buttonCart -> {
                this.articleEvent(ListenerType.CartOnClickButtonListener)
            }
        }
    }
}
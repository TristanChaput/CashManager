package epitech.eu.mobile

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ArticleActivity : AppCompatActivity(), ArticleListener {
    private val articleList = generateArticleList()
    private var cartList = ArrayList<Article>()
    private lateinit var textViewBill: TextView
    private var bill = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        val token = intent.getStringExtra("token")
        val network = intent.getStringExtra("network")

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_articles)
        textViewBill = findViewById(R.id.textView)
        recyclerView.adapter = ArticleAdapter(articleList, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        if (intent.getParcelableArrayListExtra<Parcelable>(Tools.ARRAY_INTENT_PARCELABLE) != null)
            cartList = intent.getParcelableArrayListExtra(Tools.ARRAY_INTENT_PARCELABLE)!!
        bill = intent.getDoubleExtra(Tools.BILL_INTENT_PARCELABLE, 0.00)
        this.articleEvent(ListenerType.UpdateBillOnClickButtonListener)
    }

    private fun generateArticleList(): ArrayList<Article> {
        val listArticle = ArrayList<Article>()

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
                intent.putExtra(Tools.BILL_INTENT_PARCELABLE, bill)
                intent.putParcelableArrayListExtra(Tools.ARRAY_INTENT_PARCELABLE, cartList)
                startActivity(intent)
            }
            is ListenerType.UpdateBillOnClickButtonListener -> textViewBill.text = Tools.roundInEuro(bill)
            else -> {}
        }
    }
}
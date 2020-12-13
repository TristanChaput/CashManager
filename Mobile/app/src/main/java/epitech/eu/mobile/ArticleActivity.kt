package epitech.eu.mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ArticleActivity : AppCompatActivity(), ArticleListener {
    private val articleList = generateArticleList()

    companion object {
        const val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)


        val token = intent.getStringExtra("token")
        val network = intent.getStringExtra("network")

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_articles)
        recyclerView.adapter = ArticleAdapter(articleList, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    private fun generateArticleList(): ArrayList<Article> {
        val listArticle = ArrayList<Article>()

        //A COMPLETER AVEC L'API
        listArticle.add(Article(R.drawable.croisiere, "Croisiere maldives", "", 4999.99, 0))
        listArticle.add(Article(R.drawable.croisiere, "Croisiere costa rica", "", 6299.99, 0))
        listArticle.add(Article(R.drawable.croisiere, "Croisiere iles canaries", "", 3999.99, 0))
        listArticle.add(Article(R.drawable.croisiere, "Croisiere grece", "", 7899.99, 0))
        listArticle.add(Article(R.drawable.croisiere,"Croisiere grece bis","une description",7899.99, 0))

        return listArticle
    }

    override fun articleEvent(clicked: ListenerType) {
        when (clicked) {
            is ListenerType.OnArticleClickListener -> {
                Toast.makeText(
                    this,
                    "Product n°" + (clicked.position + 1) + " clicked",
                    Toast.LENGTH_SHORT
                )
                    .show()
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(INTENT_PARCELABLE, articleList[clicked.position])
                startActivity(intent)
            }
        }
    }
}
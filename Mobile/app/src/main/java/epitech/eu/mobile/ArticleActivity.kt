package epitech.eu.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ArticleActivity : AppCompatActivity() {
    private val articleList = generateArticleList()
    private val adapter = ArticleAdapter(articleList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_articles)
        recyclerView.adapter = this.adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    fun insertArticle(view: View){
        articleList.add(Article("123", R.drawable.croisiere, "Croisiere qrcode",      "", 567.00))
        adapter.notifyItemInserted(articleList.size-1)
    }

    private fun generateArticleList(): ArrayList<Article>{
        val listArticle = ArrayList<Article>()

        //A COMPLETER AVEC L'API
        listArticle.add(Article("123", R.drawable.croisiere, "Croisiere maldives",      "", 4999.99))
        listArticle.add(Article("123", R.drawable.croisiere, "Croisiere costa rica",    "", 6299.99))
        listArticle.add(Article("123", R.drawable.croisiere, "Croisiere iles canaries", "", 3999.99))
        listArticle.add(Article("123", R.drawable.croisiere, "Croisiere grece",         "", 7899.99))
        listArticle.add(Article("123", R.drawable.croisiere, "Croisiere turquie",       "", 5666.0))
        listArticle.add(Article("123", R.drawable.croisiere, "Croisiere maroc",         "", 3000.0))
        listArticle.add(Article("123", R.drawable.croisiere, "Croisiere indonesie",     "", 3333.0))
        listArticle.add(Article("123", R.drawable.croisiere, "Croisiere las vegas",     "", 9999.99))

        return listArticle
    }
}
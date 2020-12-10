package epitech.eu.mobile

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ArticleActivity : AppCompatActivity(), ArticleAdapter.OnArticleClickListener {
    private val articleList = generateArticleList()
    private val adapter = ArticleAdapter(articleList, this)
    private var delay = 0
    private var info = false
    lateinit var mainHandler: Handler

    private val updateDelayTask = object : Runnable {
        override fun run() {
            minusOneSecondDelay()
            mainHandler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_articles)
        recyclerView.adapter = this.adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        mainHandler = Handler(Looper.getMainLooper())
    }

    fun insertArticle(view: View) {
        articleList.add(Article("123", R.drawable.croisiere, "Croisiere qrcode", "", 567.00))
        adapter.notifyItemInserted(articleList.size - 1)
    }

    override fun onArticleClick(position: Int) {
        if (delay <= 0) {
            Toast.makeText(this, "Article n°" + (position + 1) + " clicked", Toast.LENGTH_SHORT)
                .show()
            delay += 5
            info = false
            onResume()
        } else if (!info) {
            Toast.makeText(this, "Wait $delay s between every click", Toast.LENGTH_SHORT).show()
            info = true
        }
    }

    private fun generateArticleList(): ArrayList<Article> {
        val listArticle = ArrayList<Article>()

        //A COMPLETER AVEC L'API
        listArticle.add(Article("123", R.drawable.croisiere, "Croisiere maldives",     "", 4999.99))
        listArticle.add(Article("123", R.drawable.croisiere, "Croisiere costa rica",   "", 6299.99))
        listArticle.add(Article("123", R.drawable.croisiere, "Croisiere iles canaries","", 3999.99))
        listArticle.add(Article("123", R.drawable.croisiere, "Croisiere grece",        "", 7899.99))

        return listArticle
    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(updateDelayTask)
    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(updateDelayTask)
    }

    private fun minusOneSecondDelay() {
        if (delay > 0)
            delay--
        else
            onPause()
    }
}
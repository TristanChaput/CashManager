package epitech.eu.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

class Articles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        var listView = findViewById<ListView>(R.id.articles_lv)
        var list = mutableListOf<Article>()
        list.add(Article("123", R.drawable.croisiere, "Croisiere maldives",      "", 4999.99))
        list.add(Article("123", R.drawable.croisiere, "Croisiere costa rica",    "", 6299.99))
        list.add(Article("123", R.drawable.croisiere, "Croisiere iles canaries", "", 3999.99))
        list.add(Article("123", R.drawable.croisiere, "Croisiere grece",         "", 7899.99))
        list.add(Article("123", R.drawable.croisiere, "Croisiere turquie",       "", 5666.0))
        list.add(Article("123", R.drawable.croisiere, "Croisiere maroc",         "", 3000.0))
        list.add(Article("123", R.drawable.croisiere, "Croisiere indonesie",     "", 3333.0))
        list.add(Article("123", R.drawable.croisiere, "Croisiere las vegas",     "", 9999.99))

        //A COMPLETER AVEC L'API

        listView.adapter = ArticleAdapter(this, R.layout.row, list)

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            Toast.makeText(
                this@Articles,
                "You add to your cart " + list.get(position).name + " travel",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
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
        list.add(Article("123", R.drawable.croisiere, "Croisiere", "description voyage", 5.0))

        //A COMPLETER AVEC L'API

        listView.adapter = ArticleAdapter(this, R.layout.row, list)

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            Toast.makeText(
                this@Articles,
                "you select " + list.get(position).name + " travel",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
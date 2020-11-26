package epitech.eu.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class Articles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.articles)
        val listView = findViewById<ListView>(R.id.articles_lv)

        val listArticles = ArrayList<Article>()

        for (i in 0..4) {
            listArticles.add(Article("123", 0,"test", "description voyage",5.0))
        }
        //A COMPLETER AVEC L'API


        val adapter: ArrayAdapter<Article> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, listArticles)
        listView.adapter = adapter

        listView.setOnItemClickListener { adapter, view, i, id ->
            Toast.makeText(
                this,
                "Item Selected" + listArticles[i].name,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
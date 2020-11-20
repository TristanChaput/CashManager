package epitech.eu.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class Articles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.articles)
        val listView = findViewById<ListView>(R.id.articles_lv)

        val listArticles = ArrayList<Article>()

        //A COMPLETER AVEC L'API


        val adapter: ArrayAdapter<Article> = ArrayAdapter(this, android.R.layout.simple_list_item_1, listArticles)
        listView.adapter = adapter
    }
}
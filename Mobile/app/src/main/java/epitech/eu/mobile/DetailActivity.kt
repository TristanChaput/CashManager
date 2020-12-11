package epitech.eu.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val article = intent.getParcelableExtra<Article>(ArticleActivity.INTENT_PARCELABLE)

        val imageview = findViewById<ImageView>(R.id.imageView)
        val texttitle = findViewById<TextView>(R.id.textViewTitle)
        val textprice = findViewById<TextView>(R.id.textViewPrice)
        val textdescription = findViewById<TextView>(R.id.textViewDescription)

        imageview.setImageResource(article!!.img)
        texttitle.text = article.name
        textprice.text = article.prix.toString()
        textdescription.text = article.description

    }
}
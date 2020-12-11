package epitech.eu.mobile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity(), ArticleListener, View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val article = intent.getParcelableExtra<Article>(ArticleActivity.INTENT_PARCELABLE)
        val imageview = findViewById<ImageView>(R.id.imageView)
        val texttitle = findViewById<TextView>(R.id.textViewTitle)
        val textprice = findViewById<TextView>(R.id.textViewPrice)
        val textdescription = findViewById<TextView>(R.id.textViewDescription)
        val buttonAdd = findViewById<Button>(R.id.buttonAddToCart)

        imageview.setImageResource(article!!.img)
        texttitle.text = article.name
        textprice.text = article.prix.toString()
        textdescription.text = article.description
        buttonAdd.setOnClickListener(this)
    }

    override fun articleEvent(clicked: ListenerType) {
        when (clicked) {
            is ListenerType.AddOnClickButtonListener -> {
                Toast.makeText(
                    this,
                    "${clicked.button.text} was clicked",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.buttonAddToCart -> {
                this.articleEvent(ListenerType.AddOnClickButtonListener(v.findViewById(R.id.buttonAddToCart)))
            }
        }
    }
}
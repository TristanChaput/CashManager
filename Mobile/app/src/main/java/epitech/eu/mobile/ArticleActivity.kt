package epitech.eu.mobile

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.lang.reflect.Type


class ArticleActivity : AppCompatActivity(), ArticleListener, View.OnClickListener {
    private lateinit var articleList: ArrayList<Article>
    private var cartList = ArrayList<Article>()
    private lateinit var textViewBill: TextView
    private lateinit var network: String
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        token = intent.getStringExtra("token").toString()
        network = intent.getStringExtra("network").toString()
        articleList = generateArticleList()
    }

    private fun generateArticleList(): ArrayList<Article> {
        val listArticle = ArrayList<Article>()

        val url = "$network/products"

        val request = Request.Builder().method("GET", null).addHeader(
            "Content-Type",
            "application/json"
        ).addHeader(
            "Authorization",
            "Bearer $token"
        ).url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request : $e")
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response.body?.string()

                val gson = GsonBuilder().create()

                val productsListType: Type =
                    object : TypeToken<ArrayList<ProductsResponse?>?>() {}.type

                val productsList = gson.fromJson<List<ProductsResponse>>(body, productsListType)
                if (productsList != null) {
                    for ((index, value) in productsList.withIndex()) {
                        println("the element at $index is $value" + value.image)
                        listArticle.add(
                            Article(
                                "$index",
                                value.image.url,
                                value.name,
                                value.description,
                                value.price.toDouble()
                            )
                        )
                    }
                    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_articles)
                    val buttonCart = findViewById<Button>(R.id.buttonCart)
                    buttonCart.setOnClickListener(this@ArticleActivity)
                    textViewBill = findViewById(R.id.textViewBill)
                    this@ArticleActivity.runOnUiThread(Runnable {
                        recyclerView.adapter = ArticleAdapter(articleList, this@ArticleActivity)
                        recyclerView.layoutManager = LinearLayoutManager(this@ArticleActivity)
                        recyclerView.setHasFixedSize(true)
                        if (intent.getParcelableArrayListExtra<Parcelable>(Tools.ARRAY_INTENT_PARCELABLE) != null)
                            cartList =
                                intent.getParcelableArrayListExtra(Tools.ARRAY_INTENT_PARCELABLE)!!
                        textViewBill.text = Tools.computeBill(cartList)
                    })
                }
            }
        })

        return listArticle
    }

    override fun articleEvent(clicked: ListenerType) {
        when (clicked) {
            is ListenerType.OnArticleClickListener -> {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(Tools.INTENT_PARCELABLE, articleList[clicked.position])
                intent.putExtra(Tools.ARRAY_INTENT_PARCELABLE, cartList)
                startActivity(intent)
            }
            is ListenerType.CartOnClickButtonListener -> {
                val intent = Intent(this, CartActivity::class.java)
                intent.putExtra(Tools.ARRAY_INTENT_PARCELABLE, cartList)
                startActivity(intent)
            }
            else -> {
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.buttonCart -> {
                this.articleEvent(ListenerType.CartOnClickButtonListener)
            }
        }
    }
}

class ProductsResponse(val name: String, val description: String, val price: Int, val image: Image)
class Image(val url: String)
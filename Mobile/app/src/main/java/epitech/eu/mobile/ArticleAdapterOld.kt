package epitech.eu.mobile

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi

class ArticleAdapterOld(var ctx: Context, var resources: Int, var items: List<Article>) :
    ArrayAdapter<Article>(ctx, resources, items) {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(resources, null)
        val imageView: ImageView = view.findViewById(R.id.image)
        val titleTextView: TextView = view.findViewById(R.id.TextViewTitle)
        val priceTextView: TextView = view.findViewById(R.id.TextViewPrice)
        var anArticle: Article = items[position]
        imageView.setImageDrawable(ctx.resources.getDrawable(anArticle.img, null))
        titleTextView.text = anArticle.name
        priceTextView.text = anArticle.prix.toString().plus("€")
        return view
    }
}
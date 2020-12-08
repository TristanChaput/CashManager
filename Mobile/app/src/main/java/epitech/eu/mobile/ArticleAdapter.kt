package epitech.eu.mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleAdapter(private val articleList: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentItem = articleList[position]

        holder.imageView.setImageResource(currentItem.img)
        holder.textViewTitle.text = currentItem.name
        holder.textViewPrice.text = currentItem.prix.toString()
    }

    override fun getItemCount() = articleList.size

    class ArticleViewHolder(articleView: View) : RecyclerView.ViewHolder(articleView) {
        val imageView: ImageView = articleView.findViewById(R.id.image_view)
        val textViewTitle: TextView = articleView.findViewById(R.id.TextViewTitle)
        val textViewPrice: TextView = articleView.findViewById(R.id.TextViewPrice)
    }
}
package epitech.eu.mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleAdapter(private val articleList: ArrayList<Article>, private val listener: ArticleListener) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentArticle = articleList[position]

        holder.imageView.setImageResource(currentArticle.img)
        holder.textViewTitle.text = currentArticle.name
        holder.textViewPrice.text = currentArticle.prix.toString().plus("€")
    }

    override fun getItemCount() = articleList.size

    inner class ArticleViewHolder(articleView: View) : RecyclerView.ViewHolder(articleView), View.OnClickListener{
        val imageView: ImageView = articleView.findViewById(R.id.image_view)
        val textViewTitle: TextView = articleView.findViewById(R.id.TextViewName)
        val textViewPrice: TextView = articleView.findViewById(R.id.TextViewPrice)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.articleEvent(ListenerType.OnArticleClickListener(position))
            }
        }
    }
}
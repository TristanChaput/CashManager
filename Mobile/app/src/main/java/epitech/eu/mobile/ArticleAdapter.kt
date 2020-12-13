package epitech.eu.mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

/**
 * Article adapter that will format products
 * @param [articleList] list of products to adapt
 * @param [listener] implements interface that contains article event on a listener type
 */
class ArticleAdapter(private val articleList: ArrayList<Article>, private val listener: ArticleListener) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    /**
     * Article create view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(itemView)
    }

    /**
     * Article bind view holder
     */
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentArticle = articleList[position]

        Picasso.get().load(currentArticle.img).into(holder.imageView)
        holder.textViewTitle.text = currentArticle.name
        holder.textViewPrice.text = currentArticle.prix.toString().plus("€")
    }

    /**
     * Get number of items in product list
     */
    override fun getItemCount() = articleList.size

    /**
     * Article view holder
     */
    inner class ArticleViewHolder(articleView: View) : RecyclerView.ViewHolder(articleView), View.OnClickListener{
        val imageView: ImageView = articleView.findViewById(R.id.image_view)
        val textViewTitle: TextView = articleView.findViewById(R.id.TextViewName)
        val textViewPrice: TextView = articleView.findViewById(R.id.TextViewPrice)

        init {
            itemView.setOnClickListener(this)
        }

        /**
         * Call event on button click in a view
         * @param [v] the current view
         */
        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.articleEvent(ListenerType.OnArticleClickListener(position))
            }
        }
    }
}
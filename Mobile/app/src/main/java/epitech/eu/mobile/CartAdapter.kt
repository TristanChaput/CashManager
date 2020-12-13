package epitech.eu.mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CartAdapter(private val cartList: ArrayList<Article>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>(),
    View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentArticle = cartList[position]

        Picasso.get().load(currentArticle.img).into(holder.imageView)
        holder.textViewName.text = currentArticle.name
        holder.textViewPrice.text = currentArticle.prix.toString().plus("€")
        holder.textViewCount.text = Tools.countItemInCart(cartList, currentArticle)
        holder.deleteItemButton.setOnClickListener(this)
    }

    override fun getItemCount() = cartList.size

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)
        val textViewCount: TextView = itemView.findViewById(R.id.textViewCount)
        val deleteItemButton: ImageButton = itemView.findViewById(R.id.deleteItemButton)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.deleteItemButton -> {

            }
        }
    }
}
package hr.algebra.iamu_projekt.ADAPTER

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.iamu_projekt.BookPagerActivity
import hr.algebra.iamu_projekt.FRAMEWORK.startActivity
import hr.algebra.iamu_projekt.ITEM_POSITION
import hr.algebra.iamu_projekt.MODEL.BookItem
import hr.algebra.iamu_projekt.R
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class BookAdapter(private val items: MutableList<BookItem>, private val context: Context) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val ivItem: ImageView = itemView.findViewById(R.id.iv_book_image)
        private val txtItem: TextView = itemView.findViewById(R.id.tv_book_title)
        private val txtAuthor: TextView = itemView.findViewById(R.id.tv_book_author)
        private val txtRating: TextView = itemView.findViewById(R.id.tv_book_rating)
        private val txtPublished: TextView = itemView.findViewById(R.id.tv_book_published)

        fun bind(item: BookItem){
            Picasso.get()
                .load(item.cover)
                .error(R.drawable.logo)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivItem)
            txtAuthor.text = item.author
            txtItem.text = item.title
            txtRating.text = "★ " + item.rating.toString() + "/5"
            txtPublished.text = item.published.toString() + "."

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.book, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            context.startActivity<BookPagerActivity>(ITEM_POSITION, position)
        }

        holder.bind(items[position])
    }

    override fun getItemCount() = items.size


}
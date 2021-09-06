package hr.algebra.iamu_projekt.ADAPTER

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.iamu_projekt.MODEL.BookItem
import hr.algebra.iamu_projekt.R
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class BookPagerAdapter(private val items: MutableList<BookItem>, private val context: Context)
    : RecyclerView.Adapter<BookPagerAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivBook : ImageView = itemView.findViewById(R.id.ivBook)
        private val tvAuthor : TextView = itemView.findViewById(R.id.tvAuthor)
        private val tvDescription : TextView = itemView.findViewById(R.id.tvDescription)
        private val tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
        private val tvRating : TextView = itemView.findViewById(R.id.tvRating)
        private val tvPages : TextView = itemView.findViewById(R.id.tvPages)
        private val tvPublished : TextView = itemView.findViewById(R.id.tvPublished)
        private val btnDownload : Button = itemView.findViewById(R.id.button_so)

        fun bind(item: BookItem){
            Picasso.get()
                .load(item.cover)
                .error(R.drawable.logo)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivBook)
            tvAuthor.text = "by " + item.author
            tvTitle.text = item.title
            tvDescription.text = item.description
            tvRating.text = "★ " + item.rating.toString() + "/5"
            tvPages.text = item.pages.toString() + " pages"
            tvPublished.text = item.published.toString() + "."
            btnDownload.tag = item.download
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_pager, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size
}
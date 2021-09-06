package hr.algebra.iamu_projekt

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.iamu_projekt.ADAPTER.BookPagerAdapter
import hr.algebra.iamu_projekt.FRAMEWORK.fetchItems
import hr.algebra.iamu_projekt.MODEL.BookItem
import kotlinx.android.synthetic.main.activity_book_pager.*


const val ITEM_POSITION = "hr.algebra.iamu_projekt.item_position"

class BookPagerActivity : AppCompatActivity() {
    private lateinit var items: MutableList<BookItem>
    private var itemPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_pager)

        init()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun init() {
        items = fetchItems()
        itemPosition = intent.getIntExtra(ITEM_POSITION, 0)

        viewPager.adapter = BookPagerAdapter(items, this)
        viewPager.currentItem = itemPosition
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun openWebURL(view: View) {
        var uri = Uri.parse(view.tag.toString())
        val browse = Intent(Intent.ACTION_VIEW, Uri.parse(uri.toString()))
        startActivity(browse)
    }
}
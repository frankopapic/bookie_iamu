package hr.algebra.iamu_projekt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.iamu_projekt.ADAPTER.BookAdapter
import hr.algebra.iamu_projekt.FRAMEWORK.fetchItems
import hr.algebra.iamu_projekt.MODEL.BookItem
import kotlinx.android.synthetic.main.fragment_list_fragment.*

class ListFragment : Fragment() {

    private lateinit var items: MutableList<BookItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        items = requireContext().fetchItems()
        return inflater.inflate(R.layout.fragment_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemAdapter = BookAdapter(items, requireContext())
        rvItems.apply{
            layoutManager = LinearLayoutManager(activity)

            adapter = itemAdapter
        }
    }
}
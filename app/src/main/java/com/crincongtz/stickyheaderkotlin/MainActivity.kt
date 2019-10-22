package com.crincongtz.stickyheaderkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.crincongtz.stickyheaderkotlin.adapters.RecyclerViewAdapter
import com.crincongtz.stickyheaderkotlin.adapters.StickyViewAdapter
import com.crincongtz.stickyheaderkotlin.custom.HeaderItemDecoration
import com.crincongtz.stickyheaderkotlin.custom.StickyHeaderItemDecoration
import com.crincongtz.stickyheaderkotlin.models.Header
import com.crincongtz.stickyheaderkotlin.models.Item
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dummyData = mutableListOf<Any>()

        dummyData.add(Header("July 2019"))
        dummyData.add(Item("Santander", "July 22", "Transfer", "400.00"))
        dummyData.add(Item("Oxxo", "July 3", "Groceries", "-66.80"))
        dummyData.add(Item("Cinepolis", "July 2", "Entretainment", "-168.00"))
        dummyData.add(Item("Global Gas", "July 1", "Service Payment", "-200.00"))

        dummyData.add(Header("June 2019"))
        dummyData.add(Item("Cinepolis", "June 22", "Entretainment", "-168.00"))
        dummyData.add(Item("Bancomer", "June 6", "Deposit", "400.00"))
        dummyData.add(Item("Banamex", "June 4", "Visa", "-2000"))
        dummyData.add(Item("Walmart", "June 3", "Shopping", "-405.20"))
        dummyData.add(Item("Tala", "June 2", "Loan", "-1000.00"))

        dummyData.add(Header("May 2019"))
        dummyData.add(Item("Cinepolis", "May 6", "Entretainment", "-168.00"))
        dummyData.add(Item("Amazon", "May 4", "Shopping", "-249.99"))
        dummyData.add(Item("7-Eleven", "May 2", "Groceries", "-66.80"))
        dummyData.add(Item("Bodegon", "May 1", "Shopping", "-122.06"))

        dummyData.add(Header("April 2019"))
        dummyData.add(Item("Cinepolis", "April 30", "Entretainment", "-168.00"))
        dummyData.add(Item("Amazon", "April 28", "Shopping", "-456.88"))
        dummyData.add(Item("Global Gas", "April 26", "Service Payment", "-200.00"))

        dummyData.add(Header("March 2019"))
        dummyData.add(Item("Walmart", "March 31", "Shopping", "-405.20"))

        // THIS IS Java Way
/*        val listAdapter = RecyclerViewAdapter(dummyData)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
            addItemDecoration(StickyHeaderItemDecoration(listAdapter))
        }*/

        // THIS IS KOTLIN WAY
        val stickyViewAdapter = StickyViewAdapter(dummyData)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = stickyViewAdapter
        recyclerView.addItemDecoration(HeaderItemDecoration(recyclerView, isHeader()))
    }

    private fun isHeader(): (itemPosition: Int) -> Boolean {
        return {
            (recyclerView.adapter as StickyViewAdapter).adapterDataList[it] is Header
        }
    }
}

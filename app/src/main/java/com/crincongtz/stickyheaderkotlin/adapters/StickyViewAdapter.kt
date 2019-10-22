package com.crincongtz.stickyheaderkotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.crincongtz.stickyheaderkotlin.R
import com.crincongtz.stickyheaderkotlin.models.Header
import com.crincongtz.stickyheaderkotlin.models.Item

class StickyViewAdapter(dataList: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var adapterDataList: List<Any> = emptyList()

    init {
        this.adapterDataList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            HEADER_TYPE -> {
                HeaderViewHolder(layoutInflater.inflate(R.layout.row_header, parent, false))
            }
            ITEM_TYPE -> {
                ItemViewHolder(layoutInflater.inflate(R.layout.row_item, parent, false))
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val element = adapterDataList[position]
        when(holder) {
            is HeaderViewHolder -> {
                holder.header.text = (element as Header).text
            }
            is ItemViewHolder -> {
                val item = element as Item
                holder.description.text = item.description
                holder.amount.text = item.amount
                holder.date.text = item.date + " - " + item.type
            }
        }
    }

    override fun getItemCount(): Int {
        return adapterDataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(adapterDataList[position] is Header) {
            RecyclerViewAdapter.HEADER_TYPE
        } else {
            RecyclerViewAdapter.ITEM_TYPE
        }
    }

    companion object {
        const val HEADER_TYPE = 0
        const val ITEM_TYPE = 1
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var header = itemView.findViewById<TextView>(R.id.tvHeader)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var description = itemView.findViewById<TextView>(R.id.tvDescription)
        var amount = itemView.findViewById<TextView>(R.id.tvAmount)
        var date = itemView.findViewById<TextView>(R.id.tvDate)
    }
}
package com.example.demoproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject.R
import com.example.demoproject.model.ModelData
import kotlinx.android.synthetic.main.item_recyclerview.view.*

class RecyclerViewAdapter(
    val dataList: ArrayList<ModelData>,
    var clickListener: OnDataClickListener
) : RecyclerView.Adapter<RecyclerViewAdapter.DataViewHolder>() {

    class DataViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var name = view.kod

        fun initialize(item: ModelData, action: OnDataClickListener) {
            name.text = item.name

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_recyclerview, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.view.kod.text = dataList[position].name

        holder.initialize(dataList.get(position), clickListener)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateDataList(newDataList: List<ModelData>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()

    }

    interface OnDataClickListener {
        fun onItemClick(item: ModelData, posistion: Int)
    }


}
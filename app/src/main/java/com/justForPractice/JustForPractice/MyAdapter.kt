package com.justForPractice.JustForPractice

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*
import java.text.SimpleDateFormat
import java.util.*

class MyAdapter internal constructor(private val context: Context) :RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var items = emptyList<Item>()

    companion object {
        const val EXTRA_ID="PXtoEditModeID"
        const val EXTRA_TITLE="PXtoEditModeTI"
        const val EXTRA_DESCRIPTION="PXtoEditModeDE"
        const val EXTRA_DATE="PXtoEditModeDA"
    }


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view =LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val item = items[position]
        holder.itemView.txvTitle.text = item.Title
        holder.itemView.txvDescription.text = item.Description
        holder.itemView.txvDate.text = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault()).format(item.Date).toString()
        holder.itemView.setOnClickListener {
            val intent= Intent(context ,AddEditItem::class.java)
            intent.putExtra(EXTRA_ID,item.id)
            intent.putExtra(EXTRA_TITLE,item.Title)
            intent.putExtra(EXTRA_DESCRIPTION,item.Description)
            intent.putExtra(EXTRA_DATE, SimpleDateFormat("yyyy/MM/dd HH:mm",Locale.getDefault()).format(item.Date).toString())
            context.startActivity(intent)
            notifyDataSetChanged()
        }

    }
    internal fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {

            return items.size

    }
}
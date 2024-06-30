package com.vanshika.billingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BaseAdapterClass(var itemList : ArrayList<DataAdapterClass>) : BaseAdapter() {
    override fun getCount(): Int {
        return itemList.size
    }

    override fun getItem(p0: Int): Any {
        return itemList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return itemList[p0].quality?.toLong()?:0L
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(p2?.context).inflate(R.layout.item_data_adapter,p2,false)
        val tvDataName : TextView = view.findViewById(R.id.tvDataName)
        val tvDataQuantity : TextView = view.findViewById(R.id.tvDataQuantity)
        tvDataName.setText(itemList[p0].name.toString())
        tvDataQuantity.setText(itemList[p0].quality.toString())
        return view
    }
}
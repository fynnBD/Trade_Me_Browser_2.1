package com.example.trademetest20

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ListView

class categoryOnClickListener(val context : Context) : AdapterView.OnItemClickListener {

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position)
        val activity = (context as MainActivity)
        if(item == "<--")
        {
            if (activity.rootCategory?.Number?.length!! > 5)
            {
                activity.URLQuery(activity.rootCategory!!.Number?.dropLast(5))
            }
            else{
                activity.URLQuery("0")
            }
        }
        else{
            if(activity.rootCategory?.Subcategories != null) {
                for (i in activity.rootCategory?.Subcategories!!) {
                    if (i.Name == item) {
                        activity.URLQuery(i.Number)
                    }
                }
            }

        }

    }
}
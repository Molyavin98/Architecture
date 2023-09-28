package com.molyavin.mvvm.domain.models

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.molyavin.mvvm.R

@EpoxyModelClass
abstract class ItemModel : EpoxyModelWithHolder<ItemHolder>() {

    override fun getDefaultLayout(): Int {
        return R.layout.item_view
    }

    @EpoxyAttribute
    lateinit var title: String

    @EpoxyAttribute
    lateinit var description: String

    override fun bind(holder: ItemHolder) {
        holder.title.text = title
        holder.description.text = description
    }
}

class ItemHolder : EpoxyHolder() {
    lateinit var title: TextView
    lateinit var description: TextView

    override fun bindView(itemView: View) {
        title = itemView.findViewById(R.id.title)
        description = itemView.findViewById(R.id.description)
    }
}
package com.example.syntaxcodechallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.syntaxcodechallenge.R
import com.example.syntaxcodechallenge.databinding.ListItemLayoutBinding
import com.example.syntaxcodechallenge.domain.JsonResult
import com.example.syntaxcodechallenge.ui.JsonClick

/**
 * RecyclerView Adapter for setting up data binding on the items in the list.
 */
class JsonResultAdapter(val callback: JsonClick) : RecyclerView.Adapter<JsonViewHolder>() {

    /**
     * The list that the Adapter will show
     */
    var json: List<JsonResult> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JsonViewHolder {
        val withDataBinding: ListItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            JsonViewHolder.LAYOUT,
            parent,
            false)
        return JsonViewHolder(withDataBinding)
    }

    override fun getItemCount() = json.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: JsonViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.jsonData = json[position]
            it.jsonCallback = callback
        }
    }

}

/**
 * ViewHolder for attack items. All work is done by data binding.
 */
class JsonViewHolder(val viewDataBinding: ListItemLayoutBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.list_item_layout
    }
}
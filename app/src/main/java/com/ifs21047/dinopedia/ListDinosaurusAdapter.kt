package com.ifs21047.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21047.dinopedia.databinding.ItemRowDinosaurusBinding

class ListDinosaurusAdapter(private val listDinosaurus: List<Dinosaurus>) :
    RecyclerView.Adapter<ListDinosaurusAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {
        val binding =
            ItemRowDinosaurusBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dinosaurus = listDinosaurus[position]
        holder.binding.ivItemDinosaurus.setImageResource(dinosaurus.icon)
        holder.binding.tvItemDinosaurus.text = dinosaurus.name
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClicked(listDinosaurus[holder.adapterPosition])
        }
    }
    override fun getItemCount(): Int = listDinosaurus.size
    class ListViewHolder(var binding: ItemRowDinosaurusBinding) :
        RecyclerView.ViewHolder(binding.root)
    interface OnItemClickCallback {
        fun onItemClicked(data: Dinosaurus)
    }
}
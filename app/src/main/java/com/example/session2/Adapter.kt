package com.example.session2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.session2.databinding.ItemModelBinding

class Adapter(private val list: ArrayList<ItemModel>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemModelBinding) : RecyclerView.ViewHolder(binding.root) {
        val photo = binding.image
        val name = binding.textView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemModelBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.name.text = item.name
        holder.photo.setOnClickListener {
            Toast.makeText(it.context, position.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

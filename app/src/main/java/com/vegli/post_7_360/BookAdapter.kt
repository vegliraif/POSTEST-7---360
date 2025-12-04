package com.vegli.post_7_360

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.vegli.post_7_360.databinding.ItemBookBinding

class BookAdapter(private var list: List<Book>) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(book: Book) {

            binding.txtTitle.text = book.title ?: ""
            binding.txtDate.text = formatDate(book.releaseDate ?: "")


            Glide.with(binding.root.context)
                .load(book.cover)
                .into(binding.imgCover)

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, BookDetailActivity::class.java)
                intent.putExtra("title", book.title)
                intent.putExtra("date", book.releaseDate)
                intent.putExtra("cover", book.cover)  
                intent.putExtra("description", book.description)
                binding.root.context.startActivity(intent)
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun formatDate(dateString: String): String {
            return try {
                val input = java.time.LocalDate.parse(dateString)
                val formatter = java.time.format.DateTimeFormatter.ofPattern("MMM d, yyyy")
                input.format(formatter)
            } catch (e: Exception) {
                dateString
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

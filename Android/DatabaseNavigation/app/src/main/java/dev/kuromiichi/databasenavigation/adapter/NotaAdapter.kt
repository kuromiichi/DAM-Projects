package dev.kuromiichi.databasenavigation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.kuromiichi.databasenavigation.R
import dev.kuromiichi.databasenavigation.databinding.ItemNotaBinding
import dev.kuromiichi.databasenavigation.entity.Nota

class NotaAdapter(
    private val lista: List<Nota>,
    private val listener: NotaOnClickListener
) : RecyclerView.Adapter<NotaAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemNotaBinding.bind(view)

        fun bind(nota: Nota) {
            binding.text.text = nota.title
            Glide.with(binding.image)
                .load(nota.image)
                .into(binding.image)
        }

        fun setListener(nota: Nota) {
            binding.cardView.setOnClickListener {
                listener.onClick(nota)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lista[position])
        holder.setListener(lista[position])
    }

    override fun getItemCount(): Int = lista.size
}

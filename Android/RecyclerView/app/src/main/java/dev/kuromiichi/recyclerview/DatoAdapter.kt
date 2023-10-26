package dev.kuromiichi.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.kuromiichi.recyclerview.databinding.ItemDatoBinding

class DatoAdapter(
    private val lista: List<Dato>,
    private val listener: DatoOnClickListener
) : RecyclerView.Adapter<DatoAdapter.ViewHolder>() {
    inner class ViewHolder(
            view: View
    ) : RecyclerView.ViewHolder(view) {
        val binding = ItemDatoBinding.bind(view)

        fun bind(dato: Dato) {
            binding.textView.text = dato.nombre

            Glide.with(binding.imageView)
                .load(dato.imagen)
                .into(binding.imageView)
        }

        fun setListener(dato: Dato) {
            binding.root.setOnClickListener {
                listener.onClick(dato)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dato, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lista[position])
        holder.setListener(lista[position])
    }
}

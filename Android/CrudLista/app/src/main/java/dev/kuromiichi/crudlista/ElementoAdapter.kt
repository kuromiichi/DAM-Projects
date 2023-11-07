package dev.kuromiichi.crudlista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.kuromiichi.crudlista.databinding.ItemElementoBinding

class ElementoAdapter(
    private val lista: List<Elemento>,
    private val listener: ElementoOnClickListener
) : RecyclerView.Adapter<ElementoAdapter.ViewHolder>() {
    inner class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemElementoBinding.bind(view)

        fun bind(elemento: Elemento) {
            binding.nombre.text = elemento.nombre
            binding.textBoolean.text = "Boolean: ${elemento.boolean}"
            binding.textInt.text = "Int: ${elemento.int}"

            Glide.with(binding.imagen)
                .load(elemento.imagen)
                .into(binding.imagen)
        }

        fun setListener(elemento: Elemento) {
            binding.root.setOnClickListener {
                listener.onClick(elemento)
            }

            binding.root.setOnLongClickListener {
                listener.onLongClick(elemento)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_elemento, parent, false)
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

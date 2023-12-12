package dev.kuromiichi.examenmoviles.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.kuromiichi.examenmoviles.listeners.NotaOnClickListener
import dev.kuromiichi.examenmoviles.R
import dev.kuromiichi.examenmoviles.databinding.ItemNotaBinding
import dev.kuromiichi.examenmoviles.models.Nota

class NotaAdapter(
    private val listaNotas: MutableList<Nota>,
    private val listener: NotaOnClickListener
) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {
    inner class NotaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemNotaBinding.bind(view)

        fun bind(nota: Nota) {
            binding.titulo.text = nota.titulo
            binding.descripcion.text = nota.descripcion
            binding.fecha.text = nota.fechaLimite
            binding.completedBar.background = when (nota.completada) {
                true -> binding.root.context.getDrawable(R.color.green)
                false -> binding.root.context.getDrawable(R.color.red)
            }
            Glide.with(binding.imageView)
                .load(nota.imagen)
                .into(binding.imageView)
        }

        fun setListener(nota: Nota) {
            binding.root.setOnClickListener {
                listener.onClick(nota)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun getItemCount(): Int = listaNotas.size

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        holder.bind(listaNotas[position])
        holder.setListener(listaNotas[position])
    }
}
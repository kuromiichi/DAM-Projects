package dev.kuromiichi.crudlista

interface ElementoOnClickListener {
    fun onClick(elemento: Elemento)
    fun onLongClick(elemento: Elemento): Boolean
}

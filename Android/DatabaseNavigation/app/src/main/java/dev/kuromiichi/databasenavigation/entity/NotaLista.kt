package dev.kuromiichi.databasenavigation.entity

import dev.kuromiichi.databasenavigation.adapter.NotaAdapter

object NotaLista {
    var notas = mutableListOf<Nota>()
    lateinit var mAdapter: NotaAdapter
}

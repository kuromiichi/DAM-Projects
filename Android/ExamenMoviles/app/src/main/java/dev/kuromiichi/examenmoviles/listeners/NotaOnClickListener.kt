package dev.kuromiichi.examenmoviles.listeners

import dev.kuromiichi.examenmoviles.models.Nota

interface NotaOnClickListener {
    fun onClick(nota: Nota)
}
package dev.kuromiichi.examenmoviles.models

data class Nota(
    val titulo: String,
    val descripcion: String,
    val fechaCreacion: String,
    val fechaLimite: String,
    val imagen: String,
    val completada: Boolean
)
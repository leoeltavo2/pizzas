package com.example.ordenpizzasapp

data class Pedido(
    var tipoPizza: String,
    var tamaño: String,
    var extra_queso: String,
    var extra_ingrediente: String,
    var orilla_rellena: String,
    var total_pagar: String,
    var direccion: String
)

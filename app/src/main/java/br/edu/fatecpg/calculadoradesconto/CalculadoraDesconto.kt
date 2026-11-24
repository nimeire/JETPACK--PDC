package br.edu.fatecpg.calculadoradesconto

fun calcularPrecoComDesconto(preco: Double, percentualDesconto: Double): Double {
    val valorDesconto = preco * (percentualDesconto / 100.0)
    return preco - valorDesconto
}
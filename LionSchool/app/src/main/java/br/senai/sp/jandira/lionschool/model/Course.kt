package br.senai.sp.jandira.lionschool.model

//A data classe Course tem o mesmo nome dos atributos e os tipos que a minha api lionschool retorna
data class Course(
    val nome:  String,
    val sigla: String,
    val icone: String,
    val carga: String
)
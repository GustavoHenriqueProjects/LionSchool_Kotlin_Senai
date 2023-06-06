package br.senai.sp.jandira.lionschool.model

data class GraphicStudent(
    //Terceiro passo
    val nome: String,
    val foto: String,
    val disciplinas: List<DisciplineStudent>
)

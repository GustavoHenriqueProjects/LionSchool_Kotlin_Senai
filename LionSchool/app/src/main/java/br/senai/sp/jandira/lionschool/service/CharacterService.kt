package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.CourseList
import br.senai.sp.jandira.lionschool.model.StudentList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("cursos")
    fun getCharacters(): Call<CourseList>

    //https://lionschool.onrender.com/v1/lion-school/alunos/curso?curso=ds
    @GET("alunos/curso")
    fun getStudentsByCourse(@Query("curso") curso:String): Call<StudentList>

    @GET("alunos/status/ds")
    fun getStudentsDsByStatus(@Query("status") status:String): Call<StudentList>

    @GET("alunos/curso/ds/ano-finalizacao")
    fun getStudentsDsByYear(@Query("ano") ano:String): Call<StudentList>
    @GET("alunos/status/rds")
    fun getStudentsRdsByStatus(@Query("status") status:String): Call<StudentList>

    @GET("alunos/curso/rds/ano-finalizacao")
    fun getStudentsRdsByYear(@Query("ano") ano:String): Call<StudentList>

}
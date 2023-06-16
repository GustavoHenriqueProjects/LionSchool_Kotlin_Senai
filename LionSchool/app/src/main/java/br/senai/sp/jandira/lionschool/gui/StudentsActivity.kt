package br.senai.sp.jandira.lionschool.gui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.model.Student
import br.senai.sp.jandira.lionschool.model.StudentList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val typeCourse = intent.getStringExtra("type_course")
                    InterfaceStudents(typeCourse)
                }
            }
        }
    }
}

@Composable
fun InterfaceStudents(typeCourse: String?) {

    //Operador Elvis se for null recebe ds
    val typeCourse = typeCourse ?: "ds"
    val items = if(typeCourse == "DS"){
        remember { mutableStateListOf("2018", "2022", "2023", "2024") }
    }else{
        remember { mutableStateListOf("2020","2022","2021", "2023", "2024") }
    }

    val selectedItem = remember { mutableStateOf(items.first()) }
    val expandedState = remember { mutableStateOf(false) }
    var listStudent by remember {
        mutableStateOf(listOf<Student>())
    }

    var listStudentYear by remember {
        mutableStateOf(listOf<Student>())
    }

    //Verifica qual list var ser usado
    var status by remember {
        mutableStateOf(true)
    }

    val call = RetrofitFactory().getCharacterService().getStudentsByCourse(typeCourse)
    call.enqueue(object : Callback<StudentList> {
        override fun onResponse(
            call: Call<StudentList>,
            response: Response<StudentList>
        ){
            listStudent = response.body()!!.alunos
        }

        override fun onFailure(call: Call<StudentList>, t: Throwable) {

        }
    })

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .background(color = Color(0XFF121214))
            ) {
                Button(
                    onClick = {
                        val call: Call<StudentList> = if (typeCourse == "DS") {
                            RetrofitFactory().getCharacterService().getStudentsDsByStatus("cursando")
                        } else {
                            RetrofitFactory().getCharacterService().getStudentsRdsByStatus("cursando")
                        }
                        call.enqueue(object : Callback<StudentList> {
                            override fun onResponse(call: Call<StudentList>, response: Response<StudentList>) {
                                status = true
                                listStudent = response.body()?.alunos!!
                            }

                            override fun onFailure(call: Call<StudentList>, t: Throwable) {
                                // Tratar falha na requisição
                            }
                        })
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .background(Color(0XFF3E9450)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF3E9450)),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.student_activity_button_studing),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Button(
                    onClick = {
                        val call: Call<StudentList> = if (typeCourse == "DS") {
                            RetrofitFactory().getCharacterService().getStudentsDsByStatus("finalizado")
                        } else {
                            RetrofitFactory().getCharacterService().getStudentsRdsByStatus("finalizado")
                        }
                        call.enqueue(object : Callback<StudentList> {
                            override fun onResponse(call: Call<StudentList>, response: Response<StudentList>) {
                                status = true
                                listStudent = response.body()?.alunos!!
                            }

                            override fun onFailure(call: Call<StudentList>, t: Throwable) {
                                // Tratar falha na requisição
                            }
                        })

                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .background(Color(0XFFA13F2A)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFFA13F2A)),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.student_activity_button_studing_finish),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Row() {
                    Button(
                        onClick = {
                            expandedState.value = true
                        },
                        modifier = Modifier
                            .width(120.dp)
                            .padding(16.dp)
                            .background(Color(0XFFFFC23E)),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFFFFC23E)),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.student_activity_button_Year),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    DropdownMenu(
                        expanded = expandedState.value,
                        onDismissRequest = { expandedState.value = false }
                    ) {
                        items.forEachIndexed { index, item ->
                            DropdownMenuItem(onClick = {
                                selectedItem.value = item
                                expandedState.value = false

                                val call: Call<StudentList> = if (typeCourse == "DS") {
                                    RetrofitFactory().getCharacterService().getStudentsDsByYear(selectedItem.value)
                                } else {
                                    RetrofitFactory().getCharacterService().getStudentsRdsByYear(selectedItem.value)
                                }
                                call.enqueue(object : Callback<StudentList> {
                                    override fun onResponse(call: Call<StudentList>, response: Response<StudentList>) {
                                        status = false
                                        listStudentYear = response.body()?.alunos!!
                                    }

                                    override fun onFailure(call: Call<StudentList>, t: Throwable) {
                                        // Tratar falha na requisição
                                    }
                                })

                            }) {
                                Text(
                                    text = item,
                                    modifier = Modifier.padding(top = 30.dp),
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0XFFE8E147) ,
                                    style = MaterialTheme.typography.body1
                                )
                            }
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(){
                items(if (status) listStudent else listStudentYear){ it ->
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .width(190.dp)
                            .height(270.dp)
                            .clickable {
                                val openStudentGrades =
                                    Intent(context, StudentGradesActivity::class.java)
                                openStudentGrades
                                    .putExtra("name_student", it.nome)
                                    .putExtra("photo_student", it.foto)
                                    .putExtra("sexo", it.sexo)
                                    .putExtra("registration", it.matricula)
                                    .putExtra("typeCourse", typeCourse)
                                context.startActivity(openStudentGrades)
                            },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(color = Color.Black)) {
                            Image(
                                painter = painterResource(id = R.drawable.background_students),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.FillBounds
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceAround
                            ) {
                                // Conteúdo adicional do card
                                AsyncImage(
                                    model = it.foto,
                                    contentDescription = "",
                                    modifier = Modifier.size(width = 120.dp, height = 120.dp)
                                )

                                Text(
                                    text = it.nome.uppercase(),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    modifier = Modifier.padding(16.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

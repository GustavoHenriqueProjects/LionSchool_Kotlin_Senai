package br.senai.sp.jandira.lionschool.gui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.model.GraphicStudent
import br.senai.sp.jandira.lionschool.model.GraphicStudentList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentGradesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val nameStudent = intent.getStringExtra("name_student")
                    val photoStudent = intent.getStringExtra("photo_student")
                    val registrationStudent = intent.getStringExtra("registration")
                    val sexo = intent.getStringExtra("sexo")
                    InterfaceGradesStudent(nameStudent, photoStudent, registrationStudent, sexo)
                }
            }
        }
    }
}

@Composable
fun InterfaceGradesStudent(
    nameStudent: String?,
    photoStudent: String?,
    registrationStudent: String?,
    sexo: String?
) {

    val studentName = nameStudent ?: ""
    val photo = photoStudent ?: ""
    val registration = registrationStudent ?: ""
    val sexo = sexo ?: ""
    var graphicStudentList by remember {
        mutableStateOf(listOf<GraphicStudent>())
    }

    val call = RetrofitFactory().getCharacterService()
        .getGradesStudentsByName(studentName)
    call.enqueue(object : Callback<GraphicStudentList> {
        override fun onResponse(
            call: Call<GraphicStudentList>,
            response: Response<GraphicStudentList>
        ) {
            graphicStudentList = response.body()!!.aluno
        }

        override fun onFailure(call: Call<GraphicStudentList>, t: Throwable) {

        }
    })

    val scrool = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0XFF121214)),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(
                    id = R.string.title_activity_student_grades
                ),
                modifier = Modifier
                    .padding(top = 20.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(33.dp)
            ) {
                Box(
                    modifier = Modifier.background(color = Color.Black)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.background_students),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            AsyncImage(
                                model = photo,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(
                                        width = 130.dp,
                                        height = 130.dp,
                                    )
                                    .padding(
                                        start = 13.dp,
                                        bottom = 20.dp
                                    )
                            )
                        }
                        Column(
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            Text(
                                text = studentName,
                                modifier = Modifier.padding(
                                    start = 33.dp,
                                    top = 30.dp
                                ),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0XFF20007A)
                            )

                            Text(
                                text = "Sexo: ${sexo}",
                                modifier = Modifier
                                    .padding(
                                        start = 33.dp,
                                        top = 10.dp
                                    ),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0XFF20007A)
                            )

                            Text(
                                text = "Registration: ${registration}",
                                modifier = Modifier
                                    .padding(
                                        top = 53.dp
                                    ),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0XFF20007A)
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.status_activity_student_grades_approved),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .size(
                                    width = 20.dp,
                                    height = 20.dp
                                )
                                .background(
                                    Color(0XFFAEDCB3)
                                )
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.status_activity_student_grades_disapproved),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .size(
                                    width = 20.dp,
                                    height = 20.dp
                                )
                                .background(
                                    Color(0XFFD27D77)
                                )
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.status_activity_student_grades_exam),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .size(
                                    width = 20.dp,
                                    height = 20.dp
                                )
                                .background(
                                    Color(0XFFD6EA5C)
                                )
                        )
                    }
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(30.dp)
                .background(
                    color = Color(0XFF3347B0)
                )
        ) {
            items(graphicStudentList) { aluno ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .horizontalScroll(scrool),
                    horizontalArrangement = Arrangement.SpaceAround,


                    ) {

                    aluno.disciplinas.forEach { carga ->
                        Text(
                            text = "${carga.carga} h",
                            modifier = Modifier.padding(start = 30.dp, end = 4.dp),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(27.dp))
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, top = 10.dp)
                        .horizontalScroll(scrool),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom
                ) {
                    aluno.disciplinas.forEach { media ->
                        val backgroundColumn = when {
                            media.media.toInt() < 50 -> Color(0XFFD27D77)
                            media.media.toInt() < 70 -> Color(0XFFD6EA5C)
                            else -> Color(0XFFAEDCB3)

                        }
                        Column(
                            modifier = Modifier
                                .width(70.dp)
                                .padding(start = 5.dp)
                                .background(color = backgroundColumn)
                                .height((media.media.toFloat() * 2).dp)

                        ) {

                        }
                        Spacer(modifier = Modifier.width(33.dp))
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 13.dp)
                        .horizontalScroll(scrool),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom
                ) {
                    aluno.disciplinas.forEach { sigla ->
                        Box(
                            modifier = Modifier
                                .padding(start = 29.dp, top = 13.dp)
                                .rotate(-50f)
                        ) {
                            Text(
                                text = sigla.nome,
                                modifier = Modifier.padding(top = 3.dp),
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0XFF1BE930)
                            )
                        }
                        Spacer(modifier = Modifier.width(35.dp))
                    }
                }
            }
        }
    }
}
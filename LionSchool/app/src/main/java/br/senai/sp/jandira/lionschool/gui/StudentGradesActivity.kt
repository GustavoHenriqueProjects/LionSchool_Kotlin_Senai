package br.senai.sp.jandira.lionschool.gui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage

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
fun InterfaceGradesStudent(nameStudent: String?, photoStudent: String?, registrationStudent: String?, sexo: String?){

    val studentName = nameStudent?: ""
    val photo = photoStudent?: ""
    val registration = registrationStudent?: ""
    val sexo = sexo?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
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
            Card(modifier = Modifier
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
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Row() {
                        Text(
                            text = stringResource(id = R.string.status_activity_student_grades_approved),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Row() {
                        Text(
                            text = stringResource(id = R.string.status_activity_student_grades_disapproved),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Row() {
                        Text(
                            text = stringResource(id = R.string.status_activity_student_grades_exam),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
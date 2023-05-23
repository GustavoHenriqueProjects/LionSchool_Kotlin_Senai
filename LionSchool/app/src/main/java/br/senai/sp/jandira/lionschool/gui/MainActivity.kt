package br.senai.sp.jandira.lionschool.gui

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
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.model.Course
import br.senai.sp.jandira.lionschool.model.CourseList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    InterfaceHome()
                }
            }
        }
    }
}

@Composable
fun InterfaceHome(){
    val phrase = stringResource(id = br.senai.sp.jandira.lionschool.R.string.title)
    val highlightWord = "course"

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.White)) {
            append(phrase)
        }
        val startIndex = phrase.indexOf(highlightWord)
        val endIndex = startIndex + highlightWord.length
        addStyle(style = SpanStyle(color = androidx.compose.ui.graphics.Color(0XFF3347B0)), start = startIndex, end = endIndex)
    }

    var listCourses by remember {
        mutableStateOf(listOf<Course>())
    }

    val call = RetrofitFactory().getCharacterService().getCharacters()
    call.enqueue(object : Callback<CourseList> {
        override fun onResponse(
            call: Call<CourseList>,
            response: Response<CourseList>
        ){
            listCourses = response.body()!!.cursos
        }

        override fun onFailure(call: Call<CourseList>, t: Throwable) {

        }
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xff121214)
                )
                .padding(top = 10.dp, start = 15.dp, bottom = 6.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.logo
                ),
                contentDescription = "Logo",
                modifier = Modifier.size(40.dp)
            )

            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(
                    id = R.drawable.contacts
                ),
                contentDescription = "Contacs",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .size(width = 200.dp, height = 100.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = annotatedString,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }

            LazyColumn(){
                items(listCourses){
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(190.dp)
                            .padding(
                                top = 30.dp,
                                start = 30.dp,
                                end = 30.dp
                            ),
                        shape = RoundedCornerShape(
                            topStart = 8.dp,
                            topEnd = 8.dp,
                            bottomStart = 8.dp,
                            bottomEnd = 8.dp
                        )
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = Color(0XFF3347B0),
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .width(220.dp)
                                    .padding(top = 33.dp),
                                horizontalArrangement = Arrangement.SpaceAround) {
                                AsyncImage(
                                    model = it.icone,
                                    contentDescription = "",
                                    modifier = Modifier.size(width = 60.dp, height = 50.dp)
                                )
                                Text(
                                    text = it.sigla,
                                    fontSize = 43.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )

                            }

                            Spacer(modifier = Modifier.height(13.dp))

                            Column(modifier = Modifier.fillMaxWidth(),
                            ) {
                                Text(text = it.nome,
                                    modifier = Modifier.padding(
                                        start = 20.dp,
                                        top = 10.dp),
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(33.dp))
                }
            }
        }
    }
}



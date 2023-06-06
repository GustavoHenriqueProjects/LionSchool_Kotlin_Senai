package br.senai.sp.jandira.lionschool.gui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

class ContactsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    InterfaceContacts()
                }
            }
        }
    }
}

@Composable
fun InterfaceContacts(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription ="",
                modifier = Modifier
                    .size(60.dp)
                    .padding(start = 20.dp, top = 13.dp)
            )
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.padding(top = 13.dp, start = 70.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.contacts_activity_contacts),
                modifier = Modifier.padding(
                    start = 20.dp,
                    top = 60.dp),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Row(
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        top = 14.dp
                    ),verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.phone),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = stringResource(id = R.string.contacts_activity_phone),
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        top = 14.dp
                    ),verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = stringResource(id = R.string.contacts_activity_email),
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        top = 14.dp
                    ),verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.adress),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = stringResource(id = R.string.contacts_activity_adress),
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 13.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.contacts_activity_title_social_media),
                    modifier = Modifier.padding(
                        start = 20.dp,
                        top = 60.dp),
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.youtube),
                        contentDescription = "youtube",
                        modifier = Modifier.size(60.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.twitter),
                        contentDescription = "twitter",
                        modifier = Modifier.size(60.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.instagram),
                        contentDescription = "instagram",
                        modifier = Modifier.size(60.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = "facebook",
                        modifier = Modifier.size(60.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 10.dp,
                            start = 20.dp
                        )
                ) {
                    Text(
                        text = stringResource(id = R.string.contacts_activity_title_version),
                        modifier = Modifier.padding(top = 10.dp),
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(id = R.string.contacts_activity_version_number),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = stringResource(id = R.string.contacts_activity_suport),
                        modifier = Modifier.padding(top = 10.dp),
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(id = R.string.contacts_activity_copyright),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
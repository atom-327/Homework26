package com.example.homework26

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.homework26.ui.theme.Homework26Theme
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    private val contact = Contact(
        "Сергей",
        "Иванович",
        "Пупкин",
        null,
        true,
        "+7 919 999 99 99",
        "г. Тольятти, ул. 110 лет Октября, д. 77, кв. 777",
        "PupkinSI@mail.ru"
    )

    private val nullContact = Contact(
        "Сергей",
        null,
        "Пупкин",
        R.drawable.contact_image,
        false,
        null,
        "г. Тольятти, ул. 110 лет Октября, д. 77, кв. 777",
        null
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Homework26Theme {
                ContactColumn(contact)
            }
        }
    }
}

@Composable
fun ContactColumn(contact: Contact) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ShowContactImage(contact)
        ShowName(contact)
        ShowInfo(contact)
    }
}

@Composable
fun ShowContactImage(contact: Contact) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .padding(top = 72.dp)
            .padding(bottom = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        if (contact.imageRes != null) {
            ShowImage(contact.imageRes)
        } else {
            ShowInitials(
                "${contact.name.take(1).uppercase()}${
                    contact.familyName.take(1).uppercase()
                }"
            )
        }
    }
}

@Composable
fun ShowImage(imageURL: Int) {
    Image(
        painter = painterResource(id = imageURL),
        contentDescription = null
    )
}

@Composable
fun ShowInitials(initials: String) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = null,
            tint = Color.LightGray
        )
        Text(
            text = initials, fontSize = 36.sp
        )
    }
}

@Composable
fun ShowName(contact: Contact) {
    val name = contact.name
    val surname = contact.surname
    val familyName = contact.familyName
    val isFavourite = contact.isFavorite

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            surname?.let {
                Text(
                    text = surname,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Start,
                    softWrap = true
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = familyName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            if (isFavourite) {
                Image(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    painter = painterResource(id = android.R.drawable.star_big_on),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ShowInfo(contact: Contact) {
    val resources = LocalResources.current

    val phone = contact.phone ?: "***"
    val address = contact.address
    val email = contact.email

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InfoRow(
            label = resources.getString(R.string.phone), value = phone
        )
        InfoRow(
            label = resources.getString(R.string.address), value = address
        )
        email?.let {
            InfoRow(
                label = resources.getString(R.string.email), value = it
            )
        }
    }
}

@Composable
fun InfoRow(
    label: String, value: String
) {
    Row(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 18.sp,
            modifier = Modifier
                .width(120.dp)
                .padding(end = 8.dp),
            textAlign = TextAlign.End
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start,
            softWrap = true
        )
    }
}

@Preview(name = "contact", showSystemUi = true)
@Composable
fun ContactColumnPreview() {
    ContactColumn(
        Contact(
            "Сергей",
            "Иванович",
            "Пупкин",
            null,
            true,
            "+7 919 999 99 99",
            "г. Тольятти, ул. 110 лет Октября, д. 77, кв. 777",
            "PupkinSI@mail.ru"
        )
    )
}

@Preview(name = "contact", showSystemUi = true)
@Composable
fun NullContactColumnPreview() {
    ContactColumn(
        Contact(
            "Сергей",
            null,
            "Пупкин",
            R.drawable.contact_image,
            false,
            null,
            "г. Тольятти, ул. 110 лет Октября, д. 77, кв. 777",
            null
        )
    )
}

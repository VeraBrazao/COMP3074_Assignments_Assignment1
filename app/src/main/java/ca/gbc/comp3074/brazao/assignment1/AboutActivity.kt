package ca.gbc.comp3074.brazao.assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import android.app.Activity
import androidx.compose.material3.Button
import androidx.compose.ui.text.input.BackspaceCommand
import androidx.compose.ui.unit.sp


class AboutActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AboutScreen(onGoBack = {finish()})
        }
    }
}

@Composable
fun AboutScreen(onGoBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2E6D8)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "About",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(text = " Name: Vera Afonso Brazao",
            fontSize = 18.sp)

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = " Student Id: 101428180",
            fontSize = 18.sp)

        Spacer(modifier = Modifier.height(24.dp))

        val context = LocalContext
        Button(
            onClick = onGoBack
        ) {
            Text("Go Back")
        }
    }
}
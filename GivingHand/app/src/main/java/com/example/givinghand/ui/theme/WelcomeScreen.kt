package com.example.givinghand.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.givinghand.R

@Composable
fun WelcomeScreen(
    onLoginButtonClicked: () -> Unit,
    onLoginAdminButtonClicked: () -> Unit,
    onSignupButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable._144_removebg_preview), contentDescription = "")
        Button(
            onClick = onLoginButtonClicked,
            Modifier.widthIn(min = 250.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green700)
        ) {
            Text("Log in")
        }
        Button(
            onClick = onLoginAdminButtonClicked,
            Modifier.widthIn(min = 250.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green700)
        ) {
            Text("Log in as Admin")
        }
        Button(
            onClick = onSignupButtonClicked,
            Modifier.widthIn(min = 250.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green700)
        ) {
            Text("Sign Up")
        }
    }
}

@Preview
@Composable
fun StartOrderPreview(){
    WelcomeScreen(
        onLoginButtonClicked = {},
        onLoginAdminButtonClicked = {},
        onSignupButtonClicked = {},
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    )
}
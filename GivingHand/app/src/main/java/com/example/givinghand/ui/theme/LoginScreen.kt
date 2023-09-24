package com.example.givinghand.ui.theme


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.givinghand.R
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    userViewModel: UserViewModel,
    onSubmitButtonClicked: () -> Unit

) {

    val loginMessage = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        val coroutineScope = userViewModel.viewModelScope
        Image(painter = painterResource(R.drawable._144_removebg_preview), contentDescription = "")

        TextField(
            label = { Text(
                text = "Username",
                color = Color.DarkGray
            ) },
            value = username.value,
            onValueChange = { username.value = it },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray, // Change background color
                cursorColor = Color.DarkGray, // Change cursor color
                focusedIndicatorColor = Color.Transparent, // Change focused indicator color
                unfocusedIndicatorColor = Color.Transparent, // Change unfocused indicator color
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(
                text = "Password",
                color = Color.DarkGray
            ) },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray, // Change background color
                cursorColor = Color.DarkGray, // Change cursor color
                focusedIndicatorColor = Color.Transparent, // Change focused indicator color
                unfocusedIndicatorColor = Color.Transparent, // Change unfocused indicator color
            ),
        )
        if (loginMessage.value.isNotBlank()) {
            Text(
                text = loginMessage.value,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    color = Color.Red
                ),
                modifier = Modifier.padding(top = 4.dp),
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        if (confirmAuthorizedUser(username.value, password.value, userViewModel)){
                            onSubmitButtonClicked()
                            userViewModel.setUsername(username.value.text)
                            Log.i("USERNAME1: ", userViewModel.username.value.toString())

                        }


                        else
                            loginMessage.value = "Incorrect username or password"

                    }
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Green500)
            ) {
                Text(text = "Login")
            }

        }
    }
}




private suspend fun confirmAuthorizedUser(
    username: TextFieldValue,
    password: TextFieldValue,
    userViewModel: UserViewModel
): Boolean {
    val userList = userViewModel.getAllUsers().firstOrNull()

    userList?.let { users ->
        val user = users.find { it.username == username.text }
        if (user?.password == password.text) {
            return true
        }
    }

    return false
}
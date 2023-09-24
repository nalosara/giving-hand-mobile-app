package com.example.givinghand.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.givinghand.data.Action
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color


@Composable
fun EditActionDialog(
    action: Action,
    onConfirm: (Action) -> Unit,
    onDismiss: () -> Unit
) {
    var newName by remember { mutableStateOf(action.name) }
    var newMaxVolunteers by remember { mutableStateOf(action.max_volunteers) }
    var newAddress by remember { mutableStateOf(action.address) }
    var newDescription by remember { mutableStateOf(action.description) }
    var newCurrentVolunteers by remember { mutableStateOf(action.current_volunteers) }
    var newDate by remember { mutableStateOf(action.date) }
    var newCategoryId by remember { mutableStateOf(action.category_id) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Action") },
        confirmButton = {
            Button(
                onClick = {
                    val updatedAction = action.copy(
                        name = newName,
                        max_volunteers = newMaxVolunteers,
                        address = newAddress,
                        description = newDescription,
                        current_volunteers = newCurrentVolunteers,
                        date = newDate,
                        category_id = newCategoryId
                    )
                    onConfirm(updatedAction)
                    onDismiss()
                },
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Green500)
            ) {
                Text(
                    text = "Save",
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Green500)
            ) {
                Text(
                    text = "Cancel",
                    color = Color.White
                )
            }
        },
        text = {
             EditActionDialogContent(
                 newName = newName,
                 newMaxVolunteers = newMaxVolunteers,
                 newDescription = newDescription,
                 newAddress = newAddress,
                 newDate = newDate,
                 onNameChange = { newName = it },
                 onDescriptionChange = { newDescription = it },
                 onAddressChange = { newAddress = it },
                 onDateChange = { newDate = it },
                 onMaxVolunteersChange = { newMaxVolunteers = it }
             )
        }
    )
}

@Composable
fun EditActionDialogContent(
    newName: String,
    newMaxVolunteers: Int,
    newDescription: String,
    newAddress: String,
    newDate: String,
    onNameChange: (String) -> Unit,
    onMaxVolunteersChange: (Int) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onAddressChange: (String) -> Unit,
    onDateChange: (String) -> Unit
) {
    Column() {
        TextField(
            value = newName,
            onValueChange = onNameChange,
            label = { Text("Name") },
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
            value = newMaxVolunteers.toString(),
            onValueChange = onAddressChange,
            label = { Text("Maximum volunteers") },
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
            value = newDescription,
            onValueChange = onDescriptionChange,
            label = { Text("Description") },
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
            value = newAddress,
            onValueChange = onAddressChange,
            label = { Text("Address") },
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
            value = newDate,
            onValueChange = onDateChange,
            label = { Text("Date and Time") },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray, // Change background color
                cursorColor = Color.DarkGray, // Change cursor color
                focusedIndicatorColor = Color.Transparent, // Change focused indicator color
                unfocusedIndicatorColor = Color.Transparent, // Change unfocused indicator color
            ),
        )
    }
}
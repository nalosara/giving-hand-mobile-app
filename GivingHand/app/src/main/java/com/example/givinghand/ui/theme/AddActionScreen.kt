package com.example.givinghand.ui.theme

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.givinghand.data.Action
import kotlinx.coroutines.launch

@Composable
fun AddActionScreen(
    onSubmitButtonClicked: () -> Unit

){
    val actionViewModel: ActionViewModel = viewModel(
        factory = ActionViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )

    val categoryViewModel: CategoryViewModel = viewModel(
        factory = CategoryViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )
    val message = remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 300.dp)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add Action",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )
            Spacer(modifier = Modifier.padding(10.dp))

            val name = remember { mutableStateOf(TextFieldValue()) }
            val max_volunteers = remember { mutableStateOf(TextFieldValue()) }
            val address = remember { mutableStateOf(TextFieldValue()) }
            val description = remember { mutableStateOf(TextFieldValue()) }
            val date = remember { mutableStateOf(TextFieldValue()) }
            val coroutineScope = actionViewModel.viewModelScope
            val context = LocalContext.current
            val selectedCategory = remember { mutableStateOf("Donate") }
            val categoryNames by categoryViewModel.getAllCategoryNames()
                .collectAsState(initial = emptyList())

            TextField(
                label = {
                    Text(
                        text = "Name",
                        color = Color.DarkGray
                    )
                },
                value = name.value,
                onValueChange = { name.value = it },
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
                label = {
                    Text(
                        text = "Max number of volunteers",
                        color = Color.DarkGray
                    )
                },
                value = max_volunteers.value,
                onValueChange = { max_volunteers.value = it },
                shape = RoundedCornerShape(20.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.LightGray, // Change background color
                    cursorColor = Color.DarkGray, // Change cursor color
                    focusedIndicatorColor = Color.Transparent, // Change focused indicator color
                    unfocusedIndicatorColor = Color.Transparent, // Change unfocused indicator color
                ),
            )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = {
                    Text(
                        text = "Address",
                        color = Color.DarkGray
                    )
                },
                value = address.value,
                onValueChange = { address.value = it },
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
                label = {
                    Text(
                        text = "Description",
                        color = Color.DarkGray
                    )
                },
                value = description.value,
                onValueChange = { description.value = it },
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
                label = {
                    Text(
                        text = "Date and Time",
                        color = Color.DarkGray
                    )
                },
                value = date.value,
                onValueChange = { date.value = it },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.LightGray, // Change background color
                    cursorColor = Color.DarkGray, // Change cursor color
                    focusedIndicatorColor = Color.Transparent, // Change focused indicator color
                    unfocusedIndicatorColor = Color.Transparent, // Change unfocused indicator color
                ),
            )
            Spacer(modifier = Modifier.height(20.dp))

            var isDropdownExpanded by remember { mutableStateOf(false) }
            val backgroundColor = Color.LightGray

            //val dropdownMenuScope = rememberCoroutineScope()
            val dropdownMenuHeight = 200.dp // Adjust the height as needed

            Surface(
                color = backgroundColor,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .clickable { isDropdownExpanded = !isDropdownExpanded }
                        .padding(8.dp)
                        .heightIn(min = 56.dp) // Set a minimum height for the box
                        .widthIn(max = 300.dp), // Set a maximum width for the box
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Type of action",
                        color = Color.DarkGray,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    )

                    Text(
                        text = selectedCategory.value,
                        color = Color.Black,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown Indicator",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            if (isDropdownExpanded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .offset(y = 56.dp)
                ) {
                    DropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = dropdownMenuHeight)
                    ) {
                        categoryNames.forEach { categoryName ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedCategory.value = categoryName
                                    isDropdownExpanded = false
                                }
                            ) {
                                Text(text = categoryName)
                            }
                        }
                    }
                }
            }




            val current_category by categoryViewModel.getCategoryByName(selectedCategory.value)
                .collectAsState(initial = emptyList())
            val tempCategoryId = if (current_category.isNotEmpty()) {
                current_category[0].id
            } else {
                // Handle the case when the list is empty (no category found)
                // You can assign a default value or handle the situation accordingly
                1
            }


            Spacer(modifier = Modifier.height(30.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            if (!confirmActionData(
                                    name.value,
                                    max_volunteers.value,
                                    address.value,
                                    description.value,
                                    date.value,
                                    tempCategoryId
                                )
                            )
                                message.value = "All fields must be filled in."
                            else {
                                val action= Action(
                                    name = name.value.text,
                                    max_volunteers = max_volunteers.value.text.toInt(),
                                    address = address.value.text,
                                    description = description.value.text,
                                    current_volunteers = 0,
                                    date = date.value.text,
                                    category_id = tempCategoryId
                                )

                                actionViewModel.insertAction(action)
                                onSubmitButtonClicked()
                                Toast.makeText(
                                    context,
                                    "Action added successfully!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Green500),

                    ) {
                    Text(text = "Submit")
                }

            }
        }
    }
}

fun confirmActionData(name: TextFieldValue, max_volunteers: TextFieldValue, address: TextFieldValue,
                      description: TextFieldValue, date: TextFieldValue, category_id: Int): Boolean{
    if(name.text.isNotBlank() && max_volunteers.text.isNotBlank() && address.text.isNotBlank() && category_id!=-1
        && description.text.isNotBlank() && date.text.isNotBlank()){
        return true
    }
    return false
}
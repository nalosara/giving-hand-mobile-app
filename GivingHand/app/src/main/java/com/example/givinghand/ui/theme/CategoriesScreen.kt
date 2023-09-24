package com.example.givinghand.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoriesScreen(
    onAllActionsClicked: () -> Unit,
    onDonateActionsClicked: () -> Unit,
    onAnimalCareActionsClicked: () -> Unit,
    onEnvironmentalActionsClicked: () -> Unit,
    onSocialActionsClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Select category",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = onAllActionsClicked,
            Modifier
                .widthIn(min = 350.dp)
                .height(70.dp)
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green500)
        ) {
            Text("All Actions")
        }
        Button(
            onClick = onDonateActionsClicked,
            Modifier
                .widthIn(min = 350.dp)
                .height(70.dp)
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green500)
        ) {
            Text("Donate Actions")
        }
        Button(
            onClick = onAnimalCareActionsClicked,
            Modifier
                .widthIn(min = 350.dp)
                .height(70.dp)
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green500)
        ) {
            Text("Animal Care Actions")
        }
        Button(
            onClick = onEnvironmentalActionsClicked,
            Modifier
                .widthIn(min = 350.dp)
                .height(70.dp)
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green500)
        ) {
            Text("Environmental Actions")
        }
        Button(
            onClick = onSocialActionsClicked,
            Modifier
                .widthIn(min = 350.dp)
                .height(70.dp)
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green500)
        ) {
            Text("Social Actions")
        }
    }
}
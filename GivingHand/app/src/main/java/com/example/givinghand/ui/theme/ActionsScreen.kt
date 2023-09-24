package com.example.givinghand.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.givinghand.data.ActionCategory
import kotlinx.coroutines.flow.Flow

@Composable
fun ActionListItem(action: ActionCategory,
                   modifier: Modifier = Modifier,
                   onShowActonClicked: (action: ActionCategory) -> Unit
){
    Card(
        modifier = modifier.padding(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Gray200,
        ),

    ){

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Spacer(modifier = Modifier.padding(10.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)){
                Image(painter = painterResource(id = action.category_picture), contentDescription = null,
                    modifier = Modifier.size(width = 100.dp, height = 100.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.fillMaxWidth()){
                    Text(
                        text = action.name,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = action.address)
                    Text(text = action.date)

                }

            }

            androidx.compose.material3.Button(
                onClick = { onShowActonClicked(action) },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
                ,
                colors = ButtonDefaults.buttonColors(containerColor = Green500)
            ) {
                androidx.compose.material3.Text("View")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ActionList(actions: Flow<List<ActionCategory>>,
               modifier: Modifier = Modifier,
               onShowActonClicked: (action: ActionCategory) -> Unit

) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    val actionListState by actions.collectAsState(initial = emptyList())

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = Modifier
    ) {
        LazyColumn {
            itemsIndexed(actionListState) { index, action ->

                ActionListItem(
                    action = action,
                    onShowActonClicked = onShowActonClicked,
                    modifier = modifier
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) }
                            )
                        ),


                )

            }

        }
    }
}
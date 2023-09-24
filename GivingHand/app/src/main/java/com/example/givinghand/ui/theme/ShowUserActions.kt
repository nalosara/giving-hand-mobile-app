package com.example.givinghand.ui.theme

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.givinghand.data.Action
import com.example.givinghand.data.ActionCategory
import kotlinx.coroutines.flow.Flow


@Composable
fun UserActionListItem(action: ActionCategory,
                   modifier: Modifier = Modifier,
                   onShowActonClicked: (action: ActionCategory) -> Unit
){
    Card(
        modifier = Modifier.padding(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Gray200,
        ),

        ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)){
                Image(painter = painterResource(id = action.category_picture), contentDescription = null,
                    modifier = Modifier.size(width = 100.dp, height = 100.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.fillMaxWidth()){
                    androidx.compose.material.Text(
                        text = action.name,
                        fontWeight = FontWeight.Bold
                    )
                    androidx.compose.material.Text(text = action.address)
                    androidx.compose.material.Text(text = action.date)

                }

            }

            Button(
                onClick = { onShowActonClicked(action) },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
                ,
                colors = ButtonDefaults.buttonColors(containerColor = Green500)
            ) {
                Text("View")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun UserActionList(actions: List<ActionCategory>,
               modifier: Modifier = Modifier,
               onShowActonClicked: (action: ActionCategory) -> Unit,
               onLogoutButtonClicked: () -> Unit

) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }



    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            androidx.compose.material3.Button(
                onClick = onLogoutButtonClicked,
                Modifier
                    .widthIn(min = 250.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Green500)
            ) {
                Text("Log out")
            }
            LazyColumn {
                itemsIndexed(actions) { index, action ->

                    UserActionListItem(
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
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun UserActionsScreen(userViewModel: UserViewModel, onShowActonClicked: (action: ActionCategory) -> Unit,
                      onLogoutButtonClicked: () -> Unit ){
    val actionViewModel: ActionViewModel = viewModel(
        factory = ActionViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )
    val userActionViewModel: UserActionViewModel = viewModel(
        factory = UserActionViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )
    val username = userViewModel.username.value
    val userid = if(username != null){
        userViewModel.getUserIdByUsername(username)
    } else{
        -1
    }
    val userActions by userActionViewModel.getUserActionsByUserId(userid).collectAsState(initial = emptyList())
    val actions = emptyList<ActionCategory>().toMutableList()

    userActions.forEach{userAction ->
        val actionId = userAction.action_id
        val action by actionViewModel.getActionByIdWithCategory(actionId).collectAsState(initial = emptyList())
        if(action.isNotEmpty()){
            actions += action[0]
        }
    }
    UserActionList(actions = actions, onShowActonClicked = onShowActonClicked,
        onLogoutButtonClicked = onLogoutButtonClicked
        )



}
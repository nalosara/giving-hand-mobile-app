package com.example.givinghand.ui.theme

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.givinghand.R
import com.example.givinghand.data.Action
import com.example.givinghand.data.UserAction
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ShowAction(actionId: Int, modifier: Modifier = Modifier, userViewModel: UserViewModel){
    val actionViewModel: ActionViewModel = viewModel(
        factory = ActionViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )
    val userActionViewModel: UserActionViewModel = viewModel(
        factory = UserActionViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )
    val coroutineScope = userActionViewModel.viewModelScope
    val context = LocalContext.current

    val usernameState by userViewModel.username.collectAsState() // Observe the username value

    // Access the username value
    val username = usernameState ?: ""

    Log.i("username: ", username)
    val userId = if (username != null){
        val userIdTemp by userViewModel.getUserByUsername(username).collectAsState(initial = emptyList())
        if(userIdTemp.isNotEmpty()){
            userIdTemp[0].id
        } else{
            -1
        }}
    else{
           -1
        }


    val actions by actionViewModel.getActionByIdWithCategory(actionId).collectAsState(initial = emptyList())
    val action = actions.firstOrNull()

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            if (action != null) {
                Image(painter = painterResource(id = action.category_picture), contentDescription = null)
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp)){

                        Text(
                            text = action.name,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = action.description)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = action.address)
                        Text(text = action.date)
                }
            }
        }

        val currentActionId = action?.id ?: -1

        val applied = checkIfUserAlreadyApplied(userId = userId, actionId = currentActionId, userActionViewModel = userActionViewModel)

        val currentActionTemp by actionViewModel.getActionById(currentActionId).collectAsState(initial = emptyList())

        val currentAction = if(currentActionTemp.isNotEmpty()){
            currentActionTemp[0]
        } else{
            null
        }

        Button(
            onClick = {
                if(currentAction != null){
                    coroutineScope.launch {
                        if (applied) {
                            if(currentAction.current_volunteers < currentAction.max_volunteers){
                                Log.i("Action ID: ", currentAction.id.toString())
                                Log.i("User ID: ", userId.toString())
                                val userAction: UserAction =
                                    UserAction(user_id = userId, action_id = currentActionId)
                                userActionViewModel.insertUserAction(userAction)

                                val updatedAction = currentAction.copy(current_volunteers = currentAction.current_volunteers+1)
                                actionViewModel.updateAction(updatedAction)
                                Toast.makeText(
                                    context,
                                    "You have successfully applied to this action!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else{
                                Toast.makeText(
                                    context,
                                    "There are no available spaces for this action",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else{
                            Toast.makeText(
                                context,
                                "You have already applied for this action.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }


            },
            Modifier.widthIn(min = 250.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green500)
        ) {
            Text("Apply")
        }
    }
}


@Composable
private fun checkIfUserAlreadyApplied(userId: Int, actionId: Int, userActionViewModel: UserActionViewModel): Boolean{
    val userActions by userActionViewModel.getUserActionsByUserId(userId).collectAsState(initial = emptyList())
    userActions.forEach{ userAction ->
        if(userAction.action_id == actionId){
            return false
        }
    }
    return true
}

private val sampleAction = Action(1, "Sample Description", 30, "Adresa", "desc", 6, "5-14-2023", 6)
class ActionsFlowProvider : PreviewParameterProvider<Flow<List<Action>>> {
    override val values: Sequence<Flow<List<Action>>> = sequenceOf(
        flowOf(listOf(sampleAction)), // Provide a flow with a list containing the sample action
        flowOf(emptyList()) // Provide an empty flow
    )
}


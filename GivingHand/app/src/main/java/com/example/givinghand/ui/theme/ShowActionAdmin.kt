package com.example.givinghand.ui.theme

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn

import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.givinghand.R
import com.example.givinghand.data.Action
import com.example.givinghand.data.ActionDao
import com.example.givinghand.data.Category
import com.example.givinghand.datasource.DataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@Composable
fun ShowActionAdmin(actionId: Int,
                    modifier: Modifier = Modifier,
                    actionViewModel: ActionViewModel,
                    actionDao: ActionDao,
                    viewModelScope: CoroutineScope,
                    onDeleteButtonClicked: () -> Unit
){
    val actionViewModel: ActionViewModel = viewModel(
        factory = ActionViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )
    val userActionViewModel: UserActionViewModel = viewModel(
        factory = UserActionViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )
    val actions by actionViewModel.getActionById(actionId).collectAsState(initial = emptyList())
    val action = actions.firstOrNull()
    val actionCategories by actionViewModel.getActionByIdWithCategory(actionId).collectAsState(initial = emptyList())
    val actionCategory = actionCategories.firstOrNull()
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
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
            if (action != null && actionCategory !=null) {
                Image(painter = painterResource(id = actionCategory.category_picture), contentDescription = null)
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp)){
                    Text(text = action.name, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = action.description)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = action.address)
                    Text(text = action.date)
                }
            }
        }

        Button(
            onClick = {showDialog = true},
            Modifier.widthIn(min = 250.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green500)
        ) {
            Text("Edit Action")
        }
        if (showDialog) {
            if (action != null) {
                EditActionDialog(
                    action = action,
                    onConfirm = { updatedAction ->
                        viewModelScope.launch {
                            actionDao.update(updatedAction)
                        }
                    },
                    onDismiss = { showDialog = false }
                )
            }
        }
        Button(
            onClick = {
                      if(action != null){
                          val userActionId = action.id
                          userActionViewModel.DeleteUserActionsByActionId(userActionId)
                          viewModelScope.launch {
                              actionViewModel.deleteAction(action)
                          }
                          onDeleteButtonClicked()
                          Toast.makeText(
                              context,
                              "Action successfully deleted!",
                              Toast.LENGTH_SHORT
                          ).show()
                      }

            },
            Modifier.widthIn(min = 250.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green500)
        ) {
            Text("Delete Action")
        }
    }
}

private val sampleAction = Action(1, "Sample Description", 30, "Adresa", "desc", 6, "5-14-2023", 6)
class ActionsFlowProviderr : PreviewParameterProvider<Flow<List<Action>>> {
    override val values: Sequence<Flow<List<Action>>> = sequenceOf(
        flowOf(listOf(sampleAction)), // Provide a flow with a list containing the sample action
        flowOf(emptyList()) // Provide an empty flow
    )
}
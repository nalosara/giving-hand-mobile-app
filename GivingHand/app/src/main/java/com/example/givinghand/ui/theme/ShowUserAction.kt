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
fun ShowUserAction(actionId: Int, modifier: Modifier = Modifier, userViewModel: UserViewModel,
onApplyForOtherActionsClicked: () -> Unit){
    val actionViewModel: ActionViewModel = viewModel(
        factory = ActionViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )
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
                Column(
                    modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp),
                ){
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

        Button(
            onClick = onApplyForOtherActionsClicked,
            Modifier.widthIn(min = 250.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green500)
        ) {
            Text("Apply For More Actions")
        }
    }
}






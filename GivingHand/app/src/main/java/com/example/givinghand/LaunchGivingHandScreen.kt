@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.givinghand

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.givinghand.data.ActionDao
import com.example.givinghand.data.AppDatabase
import com.example.givinghand.ui.theme.ActionList
import com.example.givinghand.ui.theme.ActionViewModel
import com.example.givinghand.ui.theme.AddActionScreen
import com.example.givinghand.ui.theme.AdminActionList
import com.example.givinghand.ui.theme.AdminLogoutScreen
import com.example.givinghand.ui.theme.CategoriesScreen
import com.example.givinghand.ui.theme.LoginAdminScreen
import com.example.givinghand.ui.theme.LoginScreen
import com.example.givinghand.ui.theme.ShowAction
import com.example.givinghand.ui.theme.ShowActionAdmin
import com.example.givinghand.ui.theme.ShowUserAction
import com.example.givinghand.ui.theme.SignUpScreen
import com.example.givinghand.ui.theme.UserActionsScreen
import com.example.givinghand.ui.theme.UserViewModel
import com.example.givinghand.ui.theme.WelcomeScreen

enum class LaunchGivingHandScreen {
    Start,
    Login,
    AdminLogin,
    AdminActions,
    Signup,
    ChooseCategory,
    AddAction,
    AdminLogout,
    ShowAction,
    ShowActionAdmin,
    DonateActions,
    SocialActions,
    EnvironmentActions,
    AnimalActions,
    AllActions,
    ShowUser,
    ShowUserAction,


}


@Composable
fun LaunchGivingHandAppBar(
    title: String,
    canNavigateBack: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSignedIn: Boolean,
    showUserProfile: () -> Unit,
    showAdminProfile: () -> Unit,
    navController: NavController,
    isAdmin: Boolean
) {
    val showBackButton = canNavigateBack
            && navController.currentDestination?.route != LaunchGivingHandScreen.Start.name
            && navController.currentDestination?.route != LaunchGivingHandScreen.ChooseCategory.name
            && navController.currentDestination?.route != LaunchGivingHandScreen.AdminActions.name
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry){
    }
    val showActions = isSignedIn  &&
            (navController.currentDestination?.route != LaunchGivingHandScreen.Login.name) &&
            (navController.currentDestination?.route != LaunchGivingHandScreen.Signup.name) &&
            (navController.currentDestination?.route != LaunchGivingHandScreen.Start.name) &&
            (navController.currentDestination?.route != LaunchGivingHandScreen.AdminLogout.name)

    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            if (showActions && !isAdmin) {
                IconButton(onClick = showUserProfile) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "User Profile"
                    )
                }
            }
            if (isAdmin) {
                IconButton(onClick = showAdminProfile) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "User Profile"
                    )
                }
            }
        },
        modifier = modifier
    )
}




@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LaunchGivingHandApp() {
    // TODO: Create Controller and initialization

    val actionViewModel: ActionViewModel = viewModel(
        factory = ActionViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )
    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )
    val appBarTitle = stringResource(R.string.app_name)
    var topAppBarTitle by remember { mutableStateOf(appBarTitle) }
    val navController = rememberNavController()
    val context = LocalContext.current
    val database: AppDatabase = AppDatabase.getDatabase(context)
    val actionDao: ActionDao = database.ActionDao()
    var signedIn by remember { mutableStateOf(false)   }
    var admin by remember { mutableStateOf(false)   }

    val viewModelScope = rememberCoroutineScope()

    val onBackHandler = {
        topAppBarTitle = appBarTitle
        navController.navigateUp()
    }

    Scaffold(
        topBar = {
            // TODO: AppBar

            LaunchGivingHandAppBar(
                title = topAppBarTitle,
                canNavigateBack = navController.previousBackStackEntry != null,
                onBackClick = { onBackHandler() },
                isSignedIn = signedIn,
                isAdmin = admin,
                navController = navController,
                showUserProfile = {
                    navController.navigate(LaunchGivingHandScreen.ShowUser.name)
                    topAppBarTitle = "Your Actions"
                },
                showAdminProfile = {
                    navController.navigate(LaunchGivingHandScreen.AdminLogout.name)
                    topAppBarTitle = "Admin Profile"
                }
            )
        }
    ) { innerPadding ->

        // TODO: Navigation host
        NavHost(
            navController = navController,
            startDestination = LaunchGivingHandScreen.Start.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = LaunchGivingHandScreen.Start.name) {
                WelcomeScreen(
                    onLoginButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.Login.name)
                        topAppBarTitle = ""
                    },
                    onLoginAdminButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.AdminLogin.name)
                        topAppBarTitle = ""
                    },
                    onSignupButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.Signup.name)
                        topAppBarTitle = ""
                    }
                )
            }

            composable(route = LaunchGivingHandScreen.Login.name) {
                LoginScreen(userViewModel = userViewModel,
                    onSubmitButtonClicked = {
                        signedIn = true
                        navController.navigate(LaunchGivingHandScreen.ChooseCategory.name) {
                            popUpTo(LaunchGivingHandScreen.Start.name) { inclusive = true }
                            topAppBarTitle = ""
                        }

                    }
                )
            }
            composable(route = LaunchGivingHandScreen.AdminLogin.name) {
                LoginAdminScreen(
                    onSubmitButtonClicked = {
                        admin = true
                        navController.navigate(LaunchGivingHandScreen.AdminActions.name)
                        topAppBarTitle = ""
                    }
                )
            }

            composable(route = LaunchGivingHandScreen.Signup.name) {
                SignUpScreen(
                    userViewModel = userViewModel,
                    onSubmitButtonClicked = {
                        signedIn = true
                        navController.navigate(LaunchGivingHandScreen.ChooseCategory.name) {
                            popUpTo(LaunchGivingHandScreen.Start.name) { inclusive = true }
                            topAppBarTitle = ""
                        }
                    }
                )
            }


            composable(route = LaunchGivingHandScreen.ChooseCategory.name) {
                CategoriesScreen(
                    onAllActionsClicked = {
                        navController.navigate(LaunchGivingHandScreen.AllActions.name)
                        topAppBarTitle = "All Actions"
                    },
                    onDonateActionsClicked = {
                        navController.navigate(LaunchGivingHandScreen.DonateActions.name)
                        topAppBarTitle = "Donate Actions"
                    },
                    onAnimalCareActionsClicked = {
                        navController.navigate(LaunchGivingHandScreen.AnimalActions.name)
                        topAppBarTitle = "Animal Care Actions"
                    },
                    onEnvironmentalActionsClicked = {
                        navController.navigate(LaunchGivingHandScreen.EnvironmentActions.name)
                        topAppBarTitle = "Environmental Actions"
                    },
                    onSocialActionsClicked = {
                        navController.navigate(LaunchGivingHandScreen.SocialActions.name)
                        topAppBarTitle = "Social Actions"
                    })
            }



            composable(route = LaunchGivingHandScreen.AllActions.name) {
                val actions = actionViewModel.getAllActionsWithCategories()
                ActionList(actions = actions, Modifier.padding(8.dp),
                    onShowActonClicked = { action ->
                        val actionId = action.id.toString()
                        navController.navigate("${LaunchGivingHandScreen.ShowAction.name}/$actionId")
                        topAppBarTitle = action.name
                    })
            }

            composable(route = LaunchGivingHandScreen.DonateActions.name) {
                val actions = actionViewModel.getActionByCategoryWithCategories(category_id = 1)
                ActionList(actions = actions, Modifier.padding(8.dp),
                    onShowActonClicked = { action ->
                        val actionId = action.id.toString()
                        navController.navigate("${LaunchGivingHandScreen.ShowAction.name}/$actionId")
                        topAppBarTitle = action.name
                    })
            }

            composable(route = LaunchGivingHandScreen.AnimalActions.name) {
                val actions = actionViewModel.getActionByCategoryWithCategories(category_id = 2)
                ActionList(actions = actions, Modifier.padding(8.dp),
                    onShowActonClicked = { action ->
                        val actionId = action.id.toString()
                        navController.navigate("${LaunchGivingHandScreen.ShowAction.name}/$actionId")
                        topAppBarTitle = action.name
                    })
            }

            composable(route = LaunchGivingHandScreen.EnvironmentActions.name) {
                val actions = actionViewModel.getActionByCategoryWithCategories(category_id = 3)
                ActionList(actions = actions, Modifier.padding(8.dp),
                    onShowActonClicked = { action ->
                        val actionId = action.id.toString()
                        navController.navigate("${LaunchGivingHandScreen.ShowAction.name}/$actionId")
                        topAppBarTitle = action.name
                    })
            }

            composable(route = LaunchGivingHandScreen.SocialActions.name) {
                val actions = actionViewModel.getActionByCategoryWithCategories(category_id = 4)
                ActionList(actions = actions, Modifier.padding(8.dp),
                    onShowActonClicked = { action ->
                        val actionId = action.id.toString()
                        navController.navigate("${LaunchGivingHandScreen.ShowAction.name}/$actionId")
                        topAppBarTitle = action.name
                    })
            }
            val tempActionAdminId = "temp"
            composable(
                route = LaunchGivingHandScreen.ShowActionAdmin.name + "/{$tempActionAdminId}",
                arguments = listOf(navArgument(tempActionAdminId) { defaultValue = "1" })
            ) { backStackEntry ->
                val actionIdTemp = backStackEntry.arguments?.getString(tempActionAdminId)!!.toInt()
                ShowActionAdmin(
                    actionIdTemp,
                    modifier = Modifier,
                    actionViewModel = actionViewModel,
                    actionDao = actionDao,
                    viewModelScope = viewModelScope,
                    onDeleteButtonClicked = {navController.navigate(LaunchGivingHandScreen.AdminActions.name)
                    topAppBarTitle = "Actions"}
                )
            }
            val tempActionId = "temp"
            composable(
                route = LaunchGivingHandScreen.ShowAction.name + "/{$tempActionId}",
                arguments = listOf(navArgument(tempActionId) { defaultValue = "1" })
            ) { backStackEntry ->
                val actionIdTemp = backStackEntry.arguments?.getString(tempActionId)!!.toInt()
                ShowAction(actionIdTemp, modifier = Modifier, userViewModel = userViewModel)
            }

            composable(route = LaunchGivingHandScreen.AdminActions.name) {
                val actions = actionViewModel.getAllActionsWithCategories()
                AdminActionList(actions = actions, Modifier.padding(8.dp),
                    onAddItemButtonClicked = { navController.navigate(LaunchGivingHandScreen.AddAction.name) },
                    onShowActonClickedAdmin = { action ->
                        val actionId = action.id.toString()
                        navController.navigate("${LaunchGivingHandScreen.ShowActionAdmin.name}/$actionId")
                        topAppBarTitle = action.name
                    })
            }

            composable(route = LaunchGivingHandScreen.AddAction.name) {
                AddActionScreen(
                    onSubmitButtonClicked = { navController.navigate(LaunchGivingHandScreen.AdminActions.name)
                                            topAppBarTitle = "Add Action"},
                )
            }

            composable(route = LaunchGivingHandScreen.ShowUser.name) {
                UserActionsScreen(userViewModel = userViewModel, onShowActonClicked = { action ->
                    val actionId = action.id.toString()
                    navController.navigate("${LaunchGivingHandScreen.ShowUserAction.name}/$actionId")
                    topAppBarTitle = action.name
                }, onLogoutButtonClicked = {
                    userViewModel.setUsername(null)
                    signedIn = false
                    topAppBarTitle = appBarTitle
                    navController.navigate(LaunchGivingHandScreen.Start.name)
                })
            }

            composable(route = LaunchGivingHandScreen.ShowUserAction.name + "/{$tempActionId}",
                arguments = listOf(navArgument(tempActionId) { defaultValue = "1" })
            ) { backStackEntry ->
                val actionIdTemp = backStackEntry.arguments?.getString(tempActionId)!!.toInt()
                ShowUserAction(actionIdTemp, modifier = Modifier, userViewModel = userViewModel,
                    onApplyForOtherActionsClicked = { navController.navigate(LaunchGivingHandScreen.ChooseCategory.name){
                        topAppBarTitle = ""
                    } })
            }

            composable(LaunchGivingHandScreen.AdminLogout.name) {
                AdminLogoutScreen(onAdminLogoutButtonClicked = {
                    admin = false
                    topAppBarTitle = appBarTitle
                    navController.navigate(LaunchGivingHandScreen.Start.name)
                })
            }
        }


    }
}
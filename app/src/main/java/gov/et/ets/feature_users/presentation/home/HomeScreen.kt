package gov.et.ets.feature_users.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.acs.ahadumonitor.feature_users.presentation.home.HomeViewModel
import gov.et.ets.R
import gov.et.ets.feature_users.domain.model.User
import gov.et.ets.feature_users.presentation.Screen
import gov.et.ets.feature_users.presentation.home.components.UserItem
import gov.et.ets.ui.theme.ETSTheme

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val isLoading = viewModel.isLoading.value
    val noResult = viewModel.noResult.value
    val emptyRequest = viewModel.emptyRequest.value
    val context = LocalContext.current

    if (noResult) Toast.makeText(context, "Network error: please make sure you connect to the right network or the server is up!", Toast.LENGTH_LONG).show()
    if (emptyRequest) Toast.makeText(context, "Please create a Host to check!", Toast.LENGTH_LONG).show()

    Scaffold(
        topBar = {
            HomeTopBar(
                onPingHosts = {viewModel.onEvent(HomeEvent.PingHosts(state.users.map { it.ip }))},
                isLoading = isLoading
            )
        },
        floatingActionButton = {
            HomeFab(
                onFabClicked = { navController.navigate(Screen.Edit.route) }
            )
        },
        content = { innerPadding ->
            HomeContent(
                modifier = Modifier.padding(innerPadding),
                onDeleteUser = { viewModel.onEvent(HomeEvent.DeleteUser(it)) },
                onEditUser = {
                    navController.navigate(
                        route = Screen.Edit.passId(it)
                    )
                },
                users = state.users
            )
        }
    )
}

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    onPingHosts: () -> Unit,
    isLoading: Boolean
) {
    var showMenu by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.users),
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        actions = {
            if(isLoading){
                CircularProgressIndicator()
            }else{
                IconButton(onClick = onPingHosts) {
                    Icon(Icons.Default.Refresh, "")
                }
            }
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.MoreVert, "")
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Settings, "")
                }
            }
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onDeleteUser: (user: User) -> Unit,
    onEditUser: (id: Int?) -> Unit,
    users: List<User> = emptyList()
) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier
    ) {
        LazyColumn {
            items(users) { user ->
                UserItem(
                    user = user,
                    onEditUser = { onEditUser(user.id) },
                    onDeleteUser = { onDeleteUser(user) }
                )
            }
        }
    }
}

@Composable
fun HomeFab(
    modifier: Modifier = Modifier,
    onFabClicked: () -> Unit = {  }
) {
    FloatingActionButton(
        onClick = onFabClicked,
        modifier = modifier
            .height(52.dp)
            .widthIn(min = 52.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(imageVector = Icons.Outlined.Add, contentDescription = stringResource(id = R.string.add_user))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserContent() {
    ETSTheme(darkTheme = false) {
        HomeContent(onDeleteUser = {}, onEditUser = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserFab() {
    ETSTheme(darkTheme = false) {
        HomeFab()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserTopBar() {
    ETSTheme(darkTheme = false) {
        HomeTopBar(
            onPingHosts = {},
            isLoading = false
        )
    }
}


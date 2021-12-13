import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.jetnote.routing.Screen
import com.example.jetnote.ui.components.TopAppBar
import com.example.jetnote.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import ui.components.AppDrawer

@ExperimentalMaterialApi
@Composable
fun FavoriteScreen(viewModel: MainViewModel){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    var onClickDialog = remember { mutableStateOf(false)  }

    Scaffold(
        topBar = {
            TopAppBar(
                title = "Library",
                icon = Icons.Filled.List,
                onIconClick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreen = Screen.Favorite,
                closeDrawerAction = {
                    coroutineScope.launch { scaffoldState.drawerState.close() }
                }
            )
        },
        content = {
            Text(text = "favorite screen")
        }
    )
}
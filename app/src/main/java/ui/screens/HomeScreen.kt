import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.routing.Screen
import com.example.jetnote.ui.components.TopAppBar
import com.example.jetnote.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import ui.components.AppDrawer

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(viewModel: MainViewModel){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit, block = {
        viewModel.getBookList()
    })

    Scaffold(
        topBar = {
            TopAppBar(
                title = "Book library",
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
                currentScreen = Screen.Home,
                closeDrawerAction = {
                    coroutineScope.launch { scaffoldState.drawerState.close() }
                }
            )
        },
        content = {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 8.dp,
                    top = 16.dp,
                    end = 8.dp,
                    bottom = 0.dp
                ),
                content = {
                    items(viewModel.bookList) { item ->
                        Book(
                            book = item,
                            onBookClick = {viewModel.onBookClick(it)}
                        )
                    }
                }
            )
        }
    )
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewHome(){
    HomeScreen(viewModel = MainViewModel())
}
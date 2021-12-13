import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetnote.domain.model.BookModel
import com.example.jetnote.routing.Screen
import com.example.jetnote.ui.components.TopAppBar
import com.example.jetnote.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import ui.components.AppDrawer

@ExperimentalMaterialApi
@Composable
fun ContentScreen(viewModel: MainViewModel){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val scroll = rememberScrollState(0)

    val bookEntry: BookModel by viewModel.bookEntry.observeAsState(BookModel())

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
                currentScreen = Screen.Home,
                closeDrawerAction = {
                    coroutineScope.launch { scaffoldState.drawerState.close() }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 0.dp
                ).verticalScroll(scroll)
            ) {
                Text(
                    text = bookEntry.name,
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)
                )
                Text(
                    text = bookEntry.content,
                    style = TextStyle( fontSize = 20.sp, textAlign = TextAlign.Justify),
                    modifier = Modifier.padding(
                        top = 10.dp
                    )
                )
            }
        }
    )
}
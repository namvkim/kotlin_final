import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.jetnote.domain.model.BookModel
import com.example.jetnote.routing.JetNotesRouter
import com.example.jetnote.routing.Screen
import com.example.jetnote.ui.components.TopAppBar
import com.example.jetnote.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import ui.components.AppDrawer

@ExperimentalMaterialApi
@Composable
fun DescriptionScreen(viewModel: MainViewModel){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val bookEntry: BookModel by viewModel.bookEntry.observeAsState(BookModel())

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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 0.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = rememberImagePainter(bookEntry.image),
                        contentDescription = null,
                        modifier = Modifier
                            .width(200.dp)
                            .height(360.dp),
                        contentScale = ContentScale.FillWidth
                    )
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = bookEntry.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text= "Author: "+bookEntry.author,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(
                                top = 10.dp,
                                bottom = 10.dp
                            )
                        )
                        Button(onClick = { JetNotesRouter.navigateTo(Screen.Content) }, colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Green
                        )) {
                            Text(
                                text = "Read",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
//                Text(
//                    text = bookEntry.name,
//                    style = TextStyle( fontSize = 30.sp, fontWeight = FontWeight.Bold ),
//                )
                Text(
                    text = bookEntry.description,
                    style = TextStyle(
                        fontSize = 20.sp,
                        textAlign = TextAlign.Justify
                    ),
                )
            }
        }
    )
}
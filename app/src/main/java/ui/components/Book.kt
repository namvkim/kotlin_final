import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.jetnote.domain.model.BookModel


@Composable
fun Book(
    book: BookModel,
    onBookClick: (BookModel)->Unit
){
    Column(
        modifier = Modifier.padding(
             start = 8.dp,
             top = 0.dp,
             end = 8.dp,
             bottom = 16.dp
        ).background(color = Color.Gray).clickable {
            onBookClick.invoke(book)
        }

    ) {
        Image(
            painter = rememberImagePainter(book.image),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(300.dp),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = book.name,
            style = TextStyle( fontSize = 20.sp, fontWeight = FontWeight.Bold ),
        )
    }
}
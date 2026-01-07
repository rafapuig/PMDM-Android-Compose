package es.rafapuig.pmdm.clean.books

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list.BookListScreenRoot
import es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list.BookListViewModel
import es.rafapuig.pmdm.clean.books.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                BookListScreenRoot(
                    viewModel = remember { BookListViewModel() }
                )
            }
        }
    }
}
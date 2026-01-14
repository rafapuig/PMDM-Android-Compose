package es.rafapuig.pmdm.clean.books

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list.BookListScreenRoot
import es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list.BookListViewModel
import es.rafapuig.pmdm.clean.books.ui.theme.PMDMComposeTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class KoinMainActivity : ComponentActivity() {

    // https://insert-koin.io/docs/reference/koin-android/viewmodel#injecting-your-viewmodel

    // Aqui todavia no esta adjuntada la actividad a la aplicación (eso ocurre en onCreate)
    // IllegalStateException: Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call
    //val viewModel = getViewModel<BookListViewModel>()

    // Utilizamos versión que usa un lazy delagate
    val bookListViewModel by viewModel<BookListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                // https://insert-koin.io/docs/reference/koin-android/viewmodel#injecting-your-viewmodel
                val viewModel = getViewModel<BookListViewModel>()

                // Dentro de un composable se usa koinViewModel
                // definido en la librería
                val vm = koinViewModel<BookListViewModel>()

                BookListScreenRoot(
                    viewModel = vm
                )
            }
        }
    }
}
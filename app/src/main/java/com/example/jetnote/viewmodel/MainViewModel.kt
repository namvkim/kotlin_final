package com.example.jetnote.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.domain.model.*
import com.example.jetnote.routing.JetNotesRouter
import com.example.jetnote.routing.Screen
import com.example.jetnote.service.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * View model used for storing the global app state.
 *
 * This view model is used for all screens.
 */
class MainViewModel() : ViewModel() {

    private  val _bookList = mutableStateListOf<BookModel>()
    var errorMessage: String by mutableStateOf("")
    val bookList: List<BookModel>
        get() = _bookList

    private var _bookEntry = MutableLiveData(BookModel())
    val bookEntry: LiveData<BookModel> = _bookEntry

    fun getBookList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _bookList.clear()
                _bookList.addAll(apiService.getBooks())
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun onBookClick(book:BookModel){
        _bookEntry.value = book
        JetNotesRouter.navigateTo(Screen.Description)
    }

//    fun onReadClick(book:BookModel){
//        _bookEntry.value = book
//        JetNotesRouter.navigateTo(Screen.Description)
//    }
}

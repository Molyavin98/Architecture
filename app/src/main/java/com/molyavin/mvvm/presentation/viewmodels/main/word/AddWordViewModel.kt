package com.molyavin.mvvm.presentation.viewmodels.main.word

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.models.WordVM
import com.molyavin.mvvm.domain.usecase.word.PostAddWordUseCase
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import com.molyavin.mvvm.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddWordViewModel @Inject constructor(
    private val postAddWordUseCase: PostAddWordUseCase,
    private val toaster: Toaster,
    router: Router,
) : BaseViewModel(router, toaster) {

    private var _englishWord = MutableStateFlow(TextFieldValue())
    val englishWord: StateFlow<TextFieldValue> = _englishWord

    private var _ukraineWord = MutableStateFlow(TextFieldValue())
    val ukraineWord: StateFlow<TextFieldValue> = _ukraineWord

    fun addWord() {
        viewModelScope.launch {
            val wordVM = WordVM(eng = englishWord.value.text, ua = ukraineWord.value.text)
            postAddWordUseCase.execute(wordVM)

            clearFields()
        }
    }

    private fun clearFields() {
        _englishWord.value = TextFieldValue("")
        _ukraineWord.value = TextFieldValue("")

        toaster.show("Word added")
    }

    fun setEnglishWords(text: TextFieldValue) {
        _englishWord.value = text
    }

    fun setUkraineWords(text: TextFieldValue) {
        _ukraineWord.value = text
    }
}
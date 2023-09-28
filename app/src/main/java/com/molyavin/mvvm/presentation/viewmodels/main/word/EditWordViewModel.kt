package com.molyavin.mvvm.presentation.viewmodels.main.word

import android.view.View
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.models.WordVM
import com.molyavin.mvvm.domain.usecase.word.EditWordUseCase
import com.molyavin.mvvm.domain.usecase.word.GetIndexWordUseCase
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import com.molyavin.mvvm.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditWordViewModel @Inject constructor(
    private val getIndexWordUseCase: GetIndexWordUseCase,
    private val editWordUseCase: EditWordUseCase,
    router: Router,
    toaster: Toaster
) : BaseViewModel(router,toaster) {

    private var _englishWord = MutableStateFlow(TextFieldValue())
    val englishWord: StateFlow<TextFieldValue> = _englishWord

    private var _ukraineWord = MutableStateFlow(TextFieldValue())
    val ukraineWord: StateFlow<TextFieldValue> = _ukraineWord

    override fun onCreateView() {
        super.onCreateView()
        editWord()
    }

    private fun editWord() {

        viewModelScope.launch {
            val wordVM = getIndexWordUseCase.execute(null)
            _englishWord.value = TextFieldValue(wordVM.eng.toString())
            _ukraineWord.value = TextFieldValue(wordVM.ua.toString())
        }
    }

    fun saveCorrection() {
        viewModelScope.launch {
            val newWordVM = WordVM(eng = englishWord.value.text, ua = ukraineWord.value.text)
            editWordUseCase.execute(newWordVM)
            navigateToBack()
        }
    }

    fun setEnglishWords(text: TextFieldValue) {
        _englishWord.value = text
    }

    fun setUkraineWords(text: TextFieldValue) {
        _ukraineWord.value = text
    }

}
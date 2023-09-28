package com.molyavin.mvvm.presentation.viewmodels.main

import android.view.View
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.models.RouterNode
import com.molyavin.mvvm.domain.models.WordVM
import com.molyavin.mvvm.domain.usecase.sharedpref.SaveWordVMUseCase
import com.molyavin.mvvm.domain.usecase.word.DeleteWordUseCase
import com.molyavin.mvvm.domain.usecase.word.GetWordListUseCase
import com.molyavin.mvvm.presentation.controllers.main.word.AddWordController
import com.molyavin.mvvm.presentation.controllers.main.word.EditWordController
import com.molyavin.mvvm.presentation.controllers.profile.ProfileController
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val getWordListUseCase: GetWordListUseCase,
    private val deleteWordUseCase: DeleteWordUseCase,
    private val saveWordVMUseCase: SaveWordVMUseCase,
    router: Router
) :
    BaseViewModel(router = router, toaster = null) {

    private var _wordsList = MutableStateFlow<List<WordVM>>(emptyList())
    val wordsList: StateFlow<List<WordVM>> = _wordsList


    private val _wordState: MutableStateFlow<PagingData<WordVM>> =
        MutableStateFlow(PagingData.empty())
    val wordState: StateFlow<PagingData<WordVM>> = _wordState


    override fun onCreateView() {
        super.onCreateView()
        download()
    }

    private fun download() {
        viewModelScope.launch {
            _isLoading.value = true
            val response = getWordListUseCase.execute(null)
            if (response != null) {
                _wordsList.value = response
                _isLoading.value = false
            }
            _isLoading.value = false
        }
    }

    fun deleteWord(id: String) {
        viewModelScope.launch {
            download()
            deleteWordUseCase.execute(id)
        }
    }

    fun editWord(id: String) {
        saveWordVMUseCase.execute(id)
    }

    fun routeToUserProfile() {
        nextScreen(RouterNode(ProfileController::class.java))
    }

    fun routeToAddWords() {
        nextScreen(RouterNode(AddWordController::class.java))
    }

    fun routerToEditWords() {
        nextScreen(RouterNode(EditWordController()::class.java))
    }

}
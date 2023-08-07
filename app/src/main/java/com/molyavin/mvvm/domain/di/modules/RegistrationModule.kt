package com.molyavin.mvvm.domain.di.modules

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.di.scope.ActivityScope
import com.molyavin.mvvm.domain.usecase.SaveUserInfoUseCase
import com.molyavin.mvvm.presentation.screens.registration.viewmodel.RegistrationViewModel
import com.molyavin.mvvm.presentation.screens.registration.viewmodel.RegistrationViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class RegistrationModule(
    private val userRepository: UserRepository,
    private val viewModelLifecycleOwner: ViewModelStoreOwner,
) {

    @Provides
    @ActivityScope
    fun provideSaveUserInfoUseCase(): SaveUserInfoUseCase = SaveUserInfoUseCase(userRepository)

    @Provides
    @ActivityScope
    fun provideRegistrationViewModel(viewModelFactory: RegistrationViewModelFactory): RegistrationViewModel =
        ViewModelProvider(
            viewModelLifecycleOwner,
            viewModelFactory
        )[RegistrationViewModel::class.java]

}
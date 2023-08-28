package com.molyavin.mvvm.domain.di.modules

import android.os.Handler
import com.molyavin.mvvm.data.repositories.SettingRepository
import com.molyavin.mvvm.data.repositories.SlideRepository
import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.di.scope.AppScope
import com.molyavin.mvvm.domain.usecase.CheckFieldUseCase
import com.molyavin.mvvm.domain.usecase.GetSlideUseCase
import com.molyavin.mvvm.domain.usecase.GetStatusRememberMeUseCase
import com.molyavin.mvvm.domain.usecase.GetStatusOnBoardingUseCase
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.domain.usecase.SetStatusOnBoardingUseCase
import com.molyavin.mvvm.domain.usecase.SaveUserInfoUseCase
import com.molyavin.mvvm.domain.usecase.SetStatusRememberMeUseCase
import com.molyavin.mvvm.domain.usecase.StartScreenUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    @AppScope
    fun provideReadUserInfoUseCase(userRepository: UserRepository): ReadUserInfoUseCase =
        ReadUserInfoUseCase(userRepository)

    @Provides
    @AppScope
    fun provideSaveUserInfoUseCase(userRepository: UserRepository): SaveUserInfoUseCase =
        SaveUserInfoUseCase(userRepository)

    @Provides
    @AppScope
    fun provideCheckFieldUseCase(userRepository: UserRepository): CheckFieldUseCase =
        CheckFieldUseCase(userRepository)

    @Provides
    @AppScope
    fun provideGetStatusOnBoardingUseCase(settingRepository: SettingRepository): GetStatusOnBoardingUseCase =
        GetStatusOnBoardingUseCase(settingRepository)

    @Provides
    @AppScope
    fun provideSetStatusOnBoardingUseCase(settingRepository: SettingRepository): SetStatusOnBoardingUseCase =
        SetStatusOnBoardingUseCase(settingRepository)

    @Provides
    @AppScope
    fun provideGetStatusRememberMeUseCase(settingRepository: SettingRepository): GetStatusRememberMeUseCase =
        GetStatusRememberMeUseCase(settingRepository)

    @Provides
    @AppScope
    fun provideSetStatusRememberMeUseCase(settingRepository: SettingRepository): SetStatusRememberMeUseCase =
        SetStatusRememberMeUseCase(settingRepository)

    @Provides
    @AppScope
    fun provideStartScreenUseCase(handler: Handler): StartScreenUseCase =
        StartScreenUseCase(handler)

    @Provides
    @AppScope
    fun provideGetSlidesUseCase(slideRepository: SlideRepository): GetSlideUseCase =
        GetSlideUseCase(slideRepository)

}
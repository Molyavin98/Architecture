package com.molyavin.mvvm.domain.usecase.auth

import com.molyavin.mvvm.data.repositories.SettingRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SetStatusRememberMeUseCaseTest {

    private val settingRepository = mockk<SettingRepository>()

    private lateinit var setStatusRememberMeUseCase: SetStatusRememberMeUseCase

    @Before
    fun setUp() {
        setStatusRememberMeUseCase = SetStatusRememberMeUseCase(settingRepository)
    }

    @Test
    fun `when execute is called, saveSetting should be invoked with correct parameters`() {

        val rememberMeValue = true
        every { settingRepository.saveSetting("RememberMe", rememberMeValue) } returns Unit

        setStatusRememberMeUseCase.execute(rememberMeValue)

        verify { settingRepository.saveSetting("RememberMe", rememberMeValue) }
    }
}

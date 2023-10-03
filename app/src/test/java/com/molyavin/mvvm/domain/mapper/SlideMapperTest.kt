package com.molyavin.mvvm.domain.mapper

import com.molyavin.mvvm.data.model.SlideDTO
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class SlideMapperTest {

    private lateinit var slideMapper: SlideMapper

    @Before
    fun setUp() {
        slideMapper = SlideMapper()
    }

    @Test
    fun `map entity to VM should correctly map SlideDTO to SlideVM`() {
        val slideDTO = SlideDTO(
            urlImage = "TestUrl",
            title = "TestTitle",
            description = "TestDescription"
        )


        val result = slideMapper.mapEntityToVM(slideDTO)

        assertEquals("TestUrl", result.urlImage)
        assertEquals("TestTitle", result.title)
        assertEquals("TestDescription", result.description)
    }
}
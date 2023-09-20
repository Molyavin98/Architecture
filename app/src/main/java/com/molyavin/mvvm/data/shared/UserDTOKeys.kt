package com.molyavin.mvvm.data.shared

import com.molyavin.mvvm.data.model.UserDTO

object UserDTOKeys {
    const val ID_KEY = "id_key"
    const val EMAIL_KEY = "email_key"
    const val URL_IMAGE_KEY = "url_image_key"
    const val CREATION_TIME_KEY = "creationTime_key"
    const val LAST_LOGIN_TIME_KEY = "lastLoginTime_key"

    fun getListUserDTOKeys(): List<String> = listOf(
        ID_KEY, EMAIL_KEY, URL_IMAGE_KEY,
        CREATION_TIME_KEY, LAST_LOGIN_TIME_KEY
    )

    fun getDataToSave(userDTO: UserDTO): HashMap<String, Any> = hashMapOf(
        ID_KEY to userDTO.id,
        EMAIL_KEY to userDTO.email,
        URL_IMAGE_KEY to userDTO.urlImage,
        CREATION_TIME_KEY to userDTO.creationTime,
        LAST_LOGIN_TIME_KEY to userDTO.lastLoginTime
    )

}
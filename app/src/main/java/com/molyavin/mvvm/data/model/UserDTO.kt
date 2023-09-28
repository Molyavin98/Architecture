package com.molyavin.mvvm.data.model


data class UserDTO(
    val id: String,
    val email: String,
    val urlImage: String,
    val creationTime: Long,
    val lastLoginTime: Long,
) : DataStoreItemDTO {

    companion object {
        const val ID_KEY = "id_key"
        const val EMAIL_KEY = "email_key"
        const val URL_IMAGE_KEY = "url_image_key"
        const val CREATION_TIME_KEY = "creationTime_key"
        const val LAST_LOGIN_TIME_KEY = "lastLoginTime_key"

        fun empty(): UserDTO = Builder().build()
    }

    override fun copyDTO(parameters: Map<String, String>): DataStoreItemDTO {
        val builder = Builder()
        parameters.onEach { entry ->
            when (entry.key) {
                ID_KEY -> builder.setId(entry.value)
                EMAIL_KEY -> builder.setEmail(entry.value)
                URL_IMAGE_KEY -> builder.setUrlImage(entry.value)
                CREATION_TIME_KEY -> builder.setCreationTime(entry.value.toLong())
                LAST_LOGIN_TIME_KEY -> builder.setLastLoginTime(entry.value.toLong())
            }
        }
        return builder.build()
    }

    override fun getItemInfo(): Map<String, String> {
        return hashMapOf(
            ID_KEY to id,
            EMAIL_KEY to email,
            URL_IMAGE_KEY to urlImage,
            CREATION_TIME_KEY to creationTime.toString(),
            LAST_LOGIN_TIME_KEY to lastLoginTime.toString(),
        )
    }

    private class Builder {
        private var id: String = ""
        private var email: String = ""
        private var urlImage: String = ""
        private var creationTime: Long = 0L
        private var lastLoginTime: Long = 0L


        fun setId(id: String): Builder {
            this.id = id
            return this
        }

        fun setEmail(email: String): Builder {
            this.email = email
            return this
        }

        fun setUrlImage(urlImage: String): Builder {
            this.urlImage = urlImage
            return this
        }

        fun setCreationTime(creationTime: Long): Builder {
            this.creationTime = creationTime
            return this
        }

        fun setLastLoginTime(lastLoginTime: Long): Builder {
            this.lastLoginTime = lastLoginTime
            return this
        }

        fun build(): UserDTO = UserDTO(
            id = id,
            email = email,
            urlImage = urlImage,
            creationTime = creationTime,
            lastLoginTime = lastLoginTime
        )

    }
}

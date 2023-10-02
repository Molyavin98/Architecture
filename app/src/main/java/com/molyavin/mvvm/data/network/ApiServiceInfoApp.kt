package com.molyavin.mvvm.data.network

import com.molyavin.mvvm.data.model.InfoDTO

class ApiServiceInfoApp {

    fun fetchInfo(): List<InfoDTO> {
        return listOf(
            InfoDTO(1, "MVVM", "Это архитектурный паттерн, особенно популярный в Android-разработке. Он разделяет код на модель (Model), представление (View) и ViewModel. ViewModel содержит бизнес-логику и коммуницирует с Model, а View отображает данные и пользовательский интерфейс."),
            InfoDTO(2, "MVP", "Еще один архитектурный паттерн, где Presenter берет на себя роль посредника между Model и View. Presenter обрабатывает бизнес-логику и обновляет View."),
            InfoDTO(3, "Conductor", "Это легковесная библиотека для Android, которая помогает с организацией и управлением переходами между экранами (например, замена Fragments)."),
            InfoDTO(4, "Retrofit2", "Это типовой клиент REST для Android и Java. Retrofit позволяет легко взаимодействовать с web-сервисами, превращая HTTP API в интерфейс Java."),
            InfoDTO(5, "OkHttp", "HTTP-клиент для Android и Java. Он эффективно, поддерживает SPDY, соединение через прокси и предоставляет интерфейс для создания запросов и ответов."),
            InfoDTO(6, "Kotlin Coroutine", "Это функциональность Kotlin, позволяющая писать асинхронный код более читаемым и понятным, минимизируя колбеки и упрощая управление параллельными задачами."),
            InfoDTO(7, "RxJava2", "Это реализация Reactive Extensions для Java. Это библиотека для компоновки асинхронных операций с наблюдаемыми последовательностями."),
            InfoDTO(8, "Epoxy", "Библиотека от Airbnb для построения сложных экранов на основе RecyclerView в Android. Она автоматизирует многие аспекты процесса."),
            InfoDTO(9, "Android Paging", "Это компонент библиотеки Android Architecture Components, который помогает загружать и отображать большие наборы данных по частям или страницам."),
            InfoDTO(10, "Data Store", "Новый способ хранения данных от Google для Android. Data Store предоставляет две реализации: Preferences DataStore и Proto DataStore. Он предназначен для замены SharedPreferences."),
        )
    }

}
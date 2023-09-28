package com.molyavin.mvvm.presentation.controllers.info

import com.airbnb.epoxy.EpoxyController
import com.molyavin.mvvm.data.model.InfoDTO
import com.molyavin.mvvm.domain.models.ItemModel_

class InfoEpoxyController : EpoxyController() {

    var items:List<InfoDTO> = emptyList()

    override fun buildModels() {
        items.forEach { itemData ->
            ItemModel_()
                .id(itemData.id)
                .title(itemData.title)
                .description(itemData.description)
                .addTo(this)
        }
    }
}

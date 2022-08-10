package com.example.tbcrevision5.model

import com.squareup.moshi.Json

class Data : ArrayList<Data.FieldSubList>(){
    class FieldSubList : ArrayList<FieldSubList.FieldSubListItem>(){
        data class FieldSubListItem(
            @Json(name = "field_id")
            val fieldId: Int?,
            @Json(name = "field_type")
            val fieldType: String?,
            @Json(name = "hint")
            val hint: String?,
            @Json(name = "icon")
            val icon: String?,
            @Json(name = "is_active")
            val isActive: Boolean?,
            @Json(name = "keyboard")
            val keyboard: String?,
            @Json(name = "required")
            val required: Boolean?
        )
    }
}
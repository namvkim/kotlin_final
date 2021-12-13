package com.example.jetnote.domain.model

import java.util.*

data class BookModel(
    val id: Int = -1,
    val name: String = "",
    val author:String="",
    val description: String = "",
    val content:String= "",
    val image: String= ""
)
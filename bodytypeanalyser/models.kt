package com.example.bodytypeanalyser

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class Product(
    val Product_ID: Int,
    val Product_Name: String,
    val Product_Description: String,
    val Tags: String,
    val Image_URL: String,
    val Price: Int
)

@Serializable
data class ClothingData(
    val tops: List<Product>,
    val bottoms: List<Product>,
    val dresses: List<Product>
)

fun parseJson(jsonString: String): ClothingData {
    return Json.decodeFromString(jsonString)
}

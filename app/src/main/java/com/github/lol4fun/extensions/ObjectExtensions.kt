package com.github.lol4fun.extensions

import com.github.lol4fun.core.model.Champion
import com.github.lol4fun.core.model.dto.ChampionsDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

val gson = Gson()

//convert a data class to a map
fun <T> T.serializeToMap(): Map<String, Any> {
    return convert()
}

//convert a map to a data class
inline fun <reified T> Map<String, Any>.toDataClass(): T {
    return convert()
}

fun <T> T.toChampionGenericObject(): Champion {
    return convert()
}

//convert an object of type I to type O
inline fun <I, reified O> I.convert(): O {
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<O>() {}.type)
}

fun Map<String, Any>.toChampionsDTO(): ChampionsDTO {
    val championsList = arrayListOf<Any>()

    this.forEach {
        championsList.add(it.value)
    }

    return ChampionsDTO(
        champions = championsList
    )
}
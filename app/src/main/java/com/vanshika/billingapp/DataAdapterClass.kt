package com.vanshika.billingapp

import androidx.core.location.LocationRequestCompat.Quality

data class DataAdapterClass(
    var name : String ?= "",
    var quality: Int ?= 0
){
    override fun toString(): String {
        return "$name"
    }

    override fun hashCode(): Int {
        return quality?:0
    }
}

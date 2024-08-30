package com.vanshika.billingapp

data class DataAdapterClass(
    var name : String ?= "",
    var quality: Int ?= 0
){
    override fun toString(): String {
        return "$name"
    }
}

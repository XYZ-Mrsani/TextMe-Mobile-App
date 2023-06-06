package com.example.textme.dataclassmodel

/*data class UserModel(
    val name:String,
    val description:String,
    val imageUrl:String
)*/

class UserModel {
    var name: String? = null
    var description: String? = null
    var imageUrl: String? =null
    var uid: String? = null

    constructor() {

    }

    constructor(name: String?, description: String?, imageUrl:String, uid: String?) {
        this.name = name
        this.description = description
        this.imageUrl = imageUrl
        this.uid = uid
    }
}

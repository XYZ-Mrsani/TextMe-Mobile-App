package com.example.textme.dataclassmodel

/*data class User(
    val name: String? = null,
    val bio: String? = null,
    var uid: String? = null
)*/

class User {
    var name: String? = null
    var bio: String? = null
   var imageUrl: String? =null
    var uid: String? = null

    constructor(){

    }

    constructor(name:String?, bio:String?, imageUrl:String?, uid:String?){
        this.name = name
        this.bio = bio
        this.imageUrl = imageUrl
        this.uid = uid
    }
}
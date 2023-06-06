package com.example.textme.dataclassmodel

class ContactUser {
    var name : String? = null
    //var number:String? = null
    var description: String? = null
    var imageUrl: String? = null

    constructor(){}

    constructor(
        name:String?,
       // number:String?,
        description: String?,
        imageUrl: String?
    ){
        this.name = name
        //this.number = number
        this.description = description
        this.imageUrl = imageUrl
    }

}
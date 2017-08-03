package me.androidbox.busbybaking.domain

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by steve on 8/3/17.
 */
class FirebaseHelper {
    var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    val SEPARATOR:String = "___"
    val CHATS_PATH:String = "chats"
    val USERS_PATH:String = "users"
    val CONTACTS_PATH:String = "contacts"

    private object SingletonHolder {
        private val INSTANCE = FirebaseHelper()
    }

    fun getAuthUserEmail(): String? {
        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        var email: String? = null

        return user!!.email
    }

}
package me.androidbox.busbybaking.login

import com.google.firebase.database.DatabaseReference
import me.androidbox.busbybaking.domain.FirebaseHelper

/**
 * Created by smason on 8/3/17.
 */
class LoginRespositoryImp : LoginRepository {
    private var firebaseHelper: FirebaseHelper
  //  var databaseReference: DatabaseReference
 //   var myUserReference: DatabaseReference

    init {
  //      firebaseHelper = FirebaseHelper.getInstance()
  //      databaseReference = firebaseHelper.getDataReference()
  //      myUserReference = firebaseHelper.getMyUserReference()
    }
    override fun signUp(email: String, password: String) {

    }

    override fun signIn(email: String, password: String) {

    }

    override fun checkAlreadyAuthenticated() {

    }
}
package me.androidbox.busbybaking.login

/**
 * Created by smason on 8/3/17.
 */
class LoginRespositoryImp : LoginRepository {
    var firebaseHelper: FirebaseHelper
    var databaseReference: DatabaseReference
    var myUserReference: DatabaseReference

    init {
        firebaseHelper = FirebaseHelper.getInstance()
        databaseReference = firebaseHelper.getDataReference()
        myUserReference = firebaseHelper.getMyUserReference()
    }
    override fun signUp(email: String, password: String) {

    }

    override fun signIn(email: String, password: String) {

    }

    override fun checkAlreadyAuthenticated() {

    }
}
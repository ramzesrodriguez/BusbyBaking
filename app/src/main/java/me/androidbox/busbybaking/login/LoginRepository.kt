package me.androidbox.busbybaking.login

/**
 * Created by smason on 8/3/17.
 */
interface LoginRepository {
    fun signUp(email: String, password: String): Unit
    fun signIn(email: String, password: String): Unit
    fun checkAlreadyAuthenticated(): Unit
}
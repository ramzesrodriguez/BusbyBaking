package me.androidbox.busbybaking.login.events

/**
 * Created by smason on 8/4/17.
 */
class LoginEvent {
    val onSignInError: Int = 0
    val onSignUpError: Int = 1
    val onSignInSuccess: Int = 2
    val onSignUpSuccess: Int = 3
    val onFailedToRecoverSession: Int = 4

    private var eventType: Int = 0
    private var errorMessage: String = ""

    fun getEventType(): Int {
        return eventType
    }

    fun setEventType(eventType: Int): Unit {
        this.eventType = eventType
    }

    fun getErrorMessage(): String {
        return errorMessage
    }

    fun setErrorMessage(errorMessage: String): Unit {
        this.errorMessage = errorMessage
    }
}

package me.androidbox.busbybaking.domain

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.swing.UIManager.put
import com.google.firebase.database.DatabaseError
import com.google.android.exoplayer2.upstream.cache.CacheUtil.getKey
import com.google.firebase.database.DataSnapshot
import org.eclipse.jface.text.templates.GlobalTemplateVariables


/**
 * Created by steve on 8/3/17.
 */
class FirebaseHelper {
    private val SEPARATOR:String = "___"
    private val CHATS_PATH:String = "chats"
    private val USERS_PATH:String = "users"
    private val CONTACTS_PATH:String = "contacts"

    private var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    private object SingletonHolder {
        val INSTANCE = FirebaseHelper()
    }

    fun getInstance(): FirebaseHelper {
        return SingletonHolder.INSTANCE
    }

    fun getAuthUserEmail(): String? {
        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser

        return user?.email
    }

    fun getUserReference(email: String): DatabaseReference {
        val emailKey: String = email.replace(".", "_")

        return databaseReference.root.child(USERS_PATH).child(emailKey)
    }

    fun getMyUserReference(): DatabaseReference {
        return getUserReference(getAuthUserEmail()!!)
    }

    fun getContactsReference(email: String): DatabaseReference {
        return getUserReference(email).child(CONTACTS_PATH)
    }

    fun getMyContactsReference(): DatabaseReference {
        return getContactsReference(getAuthUserEmail()!!)
    }

    fun getOneContactReference(mainEmail: String, childEmail: String): DatabaseReference {
        val childKey: String = childEmail.replace(".", "_")

        return getUserReference(mainEmail).child(CONTACTS_PATH).child(childKey)
    }

    fun getChatsReference(receiver: String): DatabaseReference {
        val keySender: String = getAuthUserEmail().replace(".", "_")
        val keyReceiver = receiver.replace(".", ",")

        var keyChat = keySender + SEPARATOR + keyReceiver
        if(keySender > keyReceiver) {
            keyChat = keyReceiver + SEPARATOR + keySender
        }

        return databaseReference.root.child(CHATS_PATH).child(keyChat)
    }

    fun changeUserConnectionStatus(online: Boolean): Unit {
        val updates = HashMap<String, Any>()
        updates.put("online", online)
        getMyUserReference().updateChildren(updates)

        notifyContactsOfConnectionChange(online)
    }

    fun notifyContactsOfConnectionChange(online: Boolean, signoff: Boolean): Unit {
        val myEmail: String = getAuthUserEmail()!!

        getMyContactsReference().addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(child in snapshot.children) {
                    val email = child.key
                    val reference = getOneContactReference(email, myEmail)
                    reference.setValue(online)
                }
                if (signoff) {
                    FirebaseAuth.getInstance().signOut()
                }
            }

            override fun onCancelled(firebaseError: DatabaseError) {}
        })
    }

    fun notifyContactsOfConnectionChange(online: Boolean): Unit {
        notifyContactsOfConnectionChange(online, false)
    }

    fun signOff(): Unit {
        notifyContactsOfConnectionChange(, true)
    }
}
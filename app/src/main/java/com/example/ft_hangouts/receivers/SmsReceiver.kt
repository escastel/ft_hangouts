package com.example.ft_hangouts.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import com.example.ft_hangouts.data.database.AppDatabase
import com.example.ft_hangouts.data.models.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.ft_hangouts.data.models.Contact

class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            val db = AppDatabase(context)
            val scope = CoroutineScope(Dispatchers.IO)

            for (sms in messages) {
                val senderPhone = sms.originatingAddress ?: continue
                val messageBody = sms.messageBody ?: ""
                val timestamp = System.currentTimeMillis()

                scope.launch {
                    val contacts = db.getAllContacts()

                    val incomingClean = senderPhone.replace(" ", "").replace("-", "")

                    val existingContact = contacts.find { savedContact ->
                        val savedClean = savedContact.phoneNumber.replace(" ", "").replace("-", "")
                        savedClean == incomingClean ||
                                incomingClean.endsWith(savedClean) ||
                                savedClean.endsWith(incomingClean)
                    }

                    val contactId: Long = if (existingContact != null) {
                        existingContact.id
                    } else {
                        val newContact = Contact(
                            firstName = senderPhone,
                            lastName = "",
                            phoneNumber = senderPhone,
                            email = "",
                            address = "",
                            notes = ""
                        )
                        db.createContact(newContact)
                    }

                    val newMessage = Message(
                        contactId = contactId,
                        content = messageBody,
                        timestamp = timestamp,
                        isReceived = true
                    )
                    db.addMessage(newMessage)

                    val updateIntent = Intent("com.example.ft_hangouts.REFRESH_CHAT")
                    updateIntent.setPackage(context.packageName)
                    context.sendBroadcast(updateIntent)
                }
            }
        }
    }
}

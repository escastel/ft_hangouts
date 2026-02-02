package com.example.ft_hangouts.data.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.ft_hangouts.data.models.Contact
import com.example.ft_hangouts.data.models.Conversation
import com.example.ft_hangouts.data.models.Message

class AppDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "ft_hangouts.db"
        private const val DATABASE_VERSION = 3

        const val TABLE_CONTACTS = "contacts"
        const val COLUMN_CNT_ID = "id"
        const val COLUMN_CNT_NAME = "name"
        const val COLUMN_CNT_PHONE = "phone"
        const val COLUMN_CNT_EMAIL = "email"
        const val COLUMN_CNT_ADDRESS = "address"
        const val COLUMN_CNT_NOTES = "notes"
        const val COLUMN_CNT_IMAGE = "image_uri"

        const val TABLE_MESSAGES = "messages"
        const val COLUMN_MSG_ID = "id"
        const val COLUMN_MSG_CONTACT_ID = "contact_id"
        const val COLUMN_MSG_CONTENT = "content"
        const val COLUMN_MSG_TIMESTAMP = "timestamp"
        const val COLUMN_MSG_IS_RECEIVED = "is_received"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_CONTACTS (
                $COLUMN_CNT_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_CNT_NAME TEXT,
                $COLUMN_CNT_PHONE TEXT,
                $COLUMN_CNT_EMAIL TEXT,
                $COLUMN_CNT_ADDRESS TEXT,
                $COLUMN_CNT_NOTES TEXT,
                $COLUMN_CNT_IMAGE TEXT
            )
        """.trimIndent()
        db.execSQL(createTableQuery)

        val createMessagesTableQuery = """
            CREATE TABLE $TABLE_MESSAGES (
                $COLUMN_MSG_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_MSG_CONTACT_ID INTEGER,
                $COLUMN_MSG_CONTENT TEXT,
                $COLUMN_MSG_TIMESTAMP INTEGER,
                $COLUMN_MSG_IS_RECEIVED INTEGER,
                FOREIGN KEY($COLUMN_MSG_CONTACT_ID) REFERENCES $TABLE_CONTACTS($COLUMN_CNT_ID) ON DELETE CASCADE
            )
        """.trimIndent()
        db.execSQL(createMessagesTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MESSAGES")
        onCreate(db)
    }

    fun createContact(contact: Contact): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_CNT_NAME, contact.name)
            put(COLUMN_CNT_PHONE, contact.phoneNumber)
            put(COLUMN_CNT_EMAIL, contact.email)
            put(COLUMN_CNT_ADDRESS, contact.address)
            put(COLUMN_CNT_NOTES, contact.notes)
            put(COLUMN_CNT_IMAGE, contact.imageUri)
        }
        val id = db.insert(TABLE_CONTACTS, null, values)
        return id
    }

    fun getAllContacts(): List<Contact> {
        val contactsList = mutableListOf<Contact>()
        val selectQuery = "SELECT * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase

        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val contact = Contact(
                    id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_CNT_ID)),
                    name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CNT_NAME)),
                    phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CNT_PHONE)),
                    email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CNT_EMAIL)),
                    address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CNT_ADDRESS)),
                    notes = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CNT_NOTES)),
                    imageUri = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CNT_IMAGE))
                )
                contactsList.add(contact)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return contactsList
    }

    fun getContactById(id: Long): Contact? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_CONTACTS, null, "$COLUMN_CNT_ID = ?", arrayOf(id.toString()),
            null, null, null
        )

        var contact: Contact? = null
        if (cursor.moveToFirst()) {
            contact = Contact(
                id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_CNT_ID)),
                name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CNT_NAME)),
                phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CNT_PHONE)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CNT_EMAIL)),
                address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CNT_ADDRESS)),
                notes = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CNT_NOTES)),
                imageUri = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CNT_IMAGE))
            )
        }
        cursor.close()
        return contact
    }

    fun updateContact(contact: Contact): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_CNT_NAME, contact.name)
            put(COLUMN_CNT_PHONE, contact.phoneNumber)
            put(COLUMN_CNT_EMAIL, contact.email)
            put(COLUMN_CNT_ADDRESS, contact.address)
            put(COLUMN_CNT_NOTES, contact.notes)
            put(COLUMN_CNT_IMAGE, contact.imageUri)
        }

        val count = db.update(
            TABLE_CONTACTS,
            values,
            "$COLUMN_CNT_ID = ?",
            arrayOf(contact.id.toString())
        )
        return count
    }

    fun deleteContact(id: Long) {
        val db = this.writableDatabase
        db.delete(TABLE_CONTACTS, "$COLUMN_CNT_ID = ?", arrayOf(id.toString()))
    }

    fun addMessage(message: Message): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_MSG_CONTACT_ID, message.contactId)
            put(COLUMN_MSG_CONTENT, message.content)
            put(COLUMN_MSG_TIMESTAMP, message.timestamp)
            put(COLUMN_MSG_IS_RECEIVED, if (message.isReceived) 1 else 0)
        }
        return db.insert(TABLE_MESSAGES, null, values)
    }

    fun getMessagesForContact(contactId: Long): List<Message> {
        val messages = mutableListOf<Message>()
        val db = this.readableDatabase

        val query = "SELECT * FROM $TABLE_MESSAGES WHERE $COLUMN_MSG_CONTACT_ID = ? ORDER BY $COLUMN_MSG_TIMESTAMP ASC"
        val cursor = db.rawQuery(query, arrayOf(contactId.toString()))

        if (cursor.moveToFirst()) {
            do {
                messages.add(
                    Message(
                        id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_MSG_ID)),
                        contactId = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_MSG_CONTACT_ID)),
                        content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MSG_CONTENT)),
                        timestamp = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_MSG_TIMESTAMP)),
                        isReceived = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MSG_IS_RECEIVED)) == 1
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return messages
    }

    fun getActiveConversations(): List<Conversation> {
        val conversations = mutableListOf<Conversation>()
        val db = this.readableDatabase

        val query = """
            SELECT c.$COLUMN_CNT_ID, c.$COLUMN_CNT_NAME, m.$COLUMN_MSG_CONTENT, m.$COLUMN_MSG_TIMESTAMP, c.$COLUMN_CNT_IMAGE
            FROM $TABLE_CONTACTS c
            JOIN $TABLE_MESSAGES m ON c.$COLUMN_CNT_ID = m.$COLUMN_MSG_CONTACT_ID
            WHERE m.$COLUMN_MSG_TIMESTAMP = (
                SELECT MAX($COLUMN_MSG_TIMESTAMP) 
                FROM $TABLE_MESSAGES 
                WHERE $COLUMN_MSG_CONTACT_ID = c.$COLUMN_CNT_ID
            )
            ORDER BY m.$COLUMN_MSG_TIMESTAMP DESC
        """.trimIndent()

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                try {
                    val id = cursor.getLong(0)
                    val name = cursor.getString(1)
                    val content = cursor.getString(2)
                    val time = cursor.getLong(3)
                    val imageUri = if (!cursor.isNull(4)) cursor.getString(4) else null

                    conversations.add(Conversation(id, name, content, time, imageUri))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return conversations
    }
}
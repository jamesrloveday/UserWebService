package connector

import com.mongodb.MongoClient
import objects.User
import org.litote.kmongo.KMongo
import org.litote.kmongo.*

interface DbConnector {

    fun getClient(): MongoClient = KMongo.createClient()

    fun addNewUser(user: User): String
    fun findUser(id: String): User

}

class DbConnectorImpl : DbConnector {

    val USER_STORE = "user_store"

    override fun  addNewUser(user: User): String {
        val collection = getClient().getDatabase(USER_STORE).getCollection<User>()
        collection.insertOne(user)
        return user._id
    }

    override fun findUser(id: String): User {
        val collection = getClient().getDatabase(USER_STORE).getCollection<User>()
        return collection.findOne("{_id: ${id.json}") ?: throw NullPointerException("User not found, try another id")
    }

}





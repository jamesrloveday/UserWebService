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

    override fun  addNewUser(user: User): String {
        val collection = getClient().getDatabase("user_store").getCollection<User>()
        collection.insertOne(user)
        return user._id
    }

    override fun findUser(id: String): User {
        val collection = getClient().getDatabase("user_store").getCollection<User>()
        return collection.findOne("{_id: ${id.json}") ?: throw NullPointerException("User not found, try another id")
    }

}





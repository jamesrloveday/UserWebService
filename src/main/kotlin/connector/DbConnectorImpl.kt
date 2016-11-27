package connector

import com.mongodb.MongoClient
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import objects.User
import org.litote.kmongo.KMongo
import org.litote.kmongo.*

interface DbConnector {

    fun getClient(): MongoClient = KMongo.createClient()

    fun addNewUser(user: User): String
    fun findUser(id: String): User
    fun findAllUsers(): List<User>
    fun updateUser(user: User): UpdateResult
    fun delete(user: User): DeleteResult

}

class DbConnectorImpl : DbConnector {

    val USER_STORE = "user_store"

    override fun  addNewUser(user: User): String {
        getClient().getDatabase(USER_STORE).getCollection<User>().insertOne(user)
        return user._id
    }

    override fun findUser(id: String): User {
        val collection = getClient().getDatabase(USER_STORE).getCollection<User>()
        return collection.findOne("{_id: ${id.json}") ?: throw NullPointerException("User not found, try another id")
    }

    override fun findAllUsers(): List<User> =
            getClient().getDatabase(USER_STORE).getCollection<User>().find().toMutableList()

    override fun updateUser(user: User): UpdateResult =
            getClient().getDatabase(USER_STORE).getCollection<User>().updateOne(user)

    override fun delete(user: User): DeleteResult =
            getClient().getDatabase(USER_STORE).getCollection<User>().deleteOneById(user._id)

}





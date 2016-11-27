package services

import connector.DbConnectorImpl
import objects.User
import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebService


@WebService(name = "userservice")
object UserService {

    @WebMethod(operationName = "add")
    fun addUser(@WebParam(name = "user") user: User) : Unit {
        val connector = DbConnectorImpl()
        connector.addNewUser(user)
    }
}





package objects

import com.fasterxml.jackson.annotation.JsonProperty
import org.litote.kmongo.MongoId

/**
 * Created by james on 24/11/2016.
 */

data class User(@MongoId val _id: String,
                @JsonProperty("first_name") val firstName: String?,
                @JsonProperty("last_name") val lastName: String?,
                @JsonProperty("email") val email: String,
                @JsonProperty("username") val username: String,
                @JsonProperty("phone") val phone: String,
                @JsonProperty("address") val address: Address) {
}

data class Address(@JsonProperty("first_line") val firstLine: String,
                   @JsonProperty("second_line") val secondLine: String,
                   @JsonProperty("area") val area: String,
                   @JsonProperty("region") val region: String,
                   @JsonProperty("country") val country: String,
                   @JsonProperty("postcode") val postcode: String)

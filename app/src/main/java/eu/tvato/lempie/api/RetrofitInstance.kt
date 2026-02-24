package eu.tvato.lempie.api

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import eu.tvato.lempie.user.ContentHolder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


object RetrofitInstance {
    private const val BASE_URL = "https://voyager.lemmy.ml/"

    // Start of logging stuff
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    // End of logging stuff

    val gson = GsonBuilder()
        .registerTypeAdapter(ContentHolder::class.java, UserContentDeserializer())
        .create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val api: API by lazy {
        retrofit.create(API::class.java)
    }
}

class UserContentDeserializer: JsonDeserializer<ContentHolder>{
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): ContentHolder {
        val obj = json.asJsonObject
        return when(val type = obj.get("type_").asString){
            "post" -> context.deserialize(obj, ContentHolder.PostItem::class.java)
            "comment" -> context.deserialize(obj, ContentHolder.CommentContent::class.java)
            else -> throw JsonParseException("Unknown type: $type")
        }
    }
}
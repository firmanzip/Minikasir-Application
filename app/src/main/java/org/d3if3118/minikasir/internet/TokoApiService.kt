package org.d3if3118.minikasir.internet

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3118.minikasir.model.Toko
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/firmanzip/json-asessment3/main/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface TokoApiService {
    @GET("listoko.json")
    suspend fun getToko(): List<Toko>
}

object TokoApi {
    val service: TokoApiService by lazy {
        retrofit.create(TokoApiService::class.java)
    }
    fun getTokoUrl(imageId: String): String {
        return "$BASE_URL$imageId"
    }
}
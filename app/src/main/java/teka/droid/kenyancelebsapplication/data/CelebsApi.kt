package teka.droid.kenyancelebsapplication.data

import retrofit2.http.GET

interface CelebsApi {

    @GET("/random-celeb")
    suspend fun getRandomCeleb(): Celeb

    companion object{
        const val BASE_URL = "http://192.168.0.2:8080"
    }
}
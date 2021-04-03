package com.example.trademetest20.networking




import com.example.trademetest20.datatypes.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface networkInterface {

    @GET("/v1/Categories/{id}.json")
    fun getRoot(@Path("id") groupId : String?) : Call<Category>

    @Headers("Authorization: OAuth oauth_consumer_key=\"A1AC63F0332A131A78FAC304D007E7D1\", " +
            "oauth_signature_method=\"PLAINTEXT\"," +
            " oauth_signature=\"EC7F18B17A062962C6930A8AE88B16C7&\"")
    @GET("/v1/Search/General.json")
    fun getListing(@Query("category") id : String?,
                   @Query("rows") rows : Int,
                   @Query("search_string") search_string : String?) : Call<SearchResults>

}
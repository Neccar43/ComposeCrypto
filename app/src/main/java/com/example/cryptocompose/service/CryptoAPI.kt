package com.example.cryptocompose.service

import com.example.cryptocompose.model.Crypto
import com.example.cryptocompose.model.CryptoList
import retrofit2.http.GET


interface CryptoAPI {

    @GET("cryptolist.json")
    suspend fun getCryptoList(): CryptoList

    @GET("crypto.json")
    suspend fun getCrypto(): Crypto




    /*@Headers("X-CMC_PRO_API_KEY: $API_KEY")
    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getCryptoList(@Query("start") start: Int = 1, @Query("limit") limit: Int = 100): Crypto


    *//*@Headers("X-CMC_PRO_API_KEY: $API_KEY")
    @GET("v2/cryptocurrency/info")
    suspend fun getLogo(@Query("slug") coinName: String)*//*

    @Headers("X-CMC_PRO_API_KEY: $API_KEY")
    @GET("v1/cryptocurrency/quotes/latest")
   suspend fun getCoinDataByName(
        @Query("slug") coinName: String
    ):Crypto*/

}
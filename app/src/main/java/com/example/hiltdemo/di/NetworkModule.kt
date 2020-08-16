package com.example.hiltdemo.di

import androidx.transition.Visibility
import com.example.hiltdemo.network.ApiService
import com.example.hiltdemo.network.NetworkConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Provides
    fun baseUrl() = NetworkConstants.BASE_URL

    @Provides
    fun loggingInterceptro():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun httpClient(logger: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(logger)
        okHttpClient.callTimeout(60,TimeUnit.SECONDS)
        okHttpClient.connectTimeout(60,TimeUnit.SECONDS)
        okHttpClient.writeTimeout(60,TimeUnit.SECONDS)
        okHttpClient.readTimeout(60,TimeUnit.SECONDS)

        val okHttp= okHttpClient.build()
        return okHttp
    }

    @Provides
    fun converterFactory():Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun getRetrofit(baseUrl: String,converterFactory: Converter.Factory,okHttpClient: OkHttpClient):Retrofit{
     return Retrofit.Builder()
         .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
         .client(okHttpClient)
         .build()
    }

    @Provides
    fun getAPIService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

}
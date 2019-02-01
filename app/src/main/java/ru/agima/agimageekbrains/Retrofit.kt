package ru.agima.agimageekbrains

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


var gitHubService = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
    .build()
    .create(GitHubService::class.java)

interface GitHubService {
    @GET("repositories")
    fun listRepos(): Single<List<Repository>>
}

data class Repository(
    val name: String,
    val description: String?
)
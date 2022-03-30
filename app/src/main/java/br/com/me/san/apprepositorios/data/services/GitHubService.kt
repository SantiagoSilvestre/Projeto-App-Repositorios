package br.com.me.san.apprepositorios.data.services

import br.com.me.san.apprepositorios.data.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun listRepositories(@Path("user") user: String?): List<Repo>
}
package br.com.me.san.apprepositorios.data.repositories

import br.com.me.san.apprepositorios.data.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    suspend fun listRepositories(user: String): Flow<List<Repo>>
}
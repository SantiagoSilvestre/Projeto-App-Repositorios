package br.com.me.san.apprepositorios.domain

import br.com.me.san.apprepositorios.core.UseCase
import br.com.me.san.apprepositorios.data.model.Repo
import br.com.me.san.apprepositorios.data.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow

class ListUserRepositoriesUserCase(private val repository: RepoRepository) :
    UseCase<String, List<Repo>>() {
    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }
}
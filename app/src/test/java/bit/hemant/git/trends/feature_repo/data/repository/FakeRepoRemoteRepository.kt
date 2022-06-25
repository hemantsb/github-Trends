package bit.hemant.git.trends.feature_repo.data.repository

import bit.hemant.git.trends.feature_repo.data.data_source.remote.network.GitRemoteRepo
import bit.hemant.git.trends.feature_repo.data.data_source.remote.network.Owner
import bit.hemant.git.trends.feature_repo.domain.repository.GitRemoteRepository
import kotlin.random.Random

class FakeRepoRemoteRepository : GitRemoteRepository {

    private var repos = mutableListOf<GitRemoteRepo>()

    override suspend fun getRepoList(): List<GitRemoteRepo> {
        return repos
    }

    init {
        //INIT with dummy Data
        val repoRemoteToInsert = mutableListOf<GitRemoteRepo>()
        ('a'..'z').forEachIndexed { index, c ->
            val dummyText = c.toString()
            val owner= Owner()
            owner.login = dummyText

            repoRemoteToInsert.add(
                GitRemoteRepo(
                    id = index,
                    name = dummyText,
                    owner = owner,
                    language = dummyText,
                    forkCount =index,
                    starCount = index
                )
            )
        }
        repoRemoteToInsert.shuffle()
        repos.addAll(repoRemoteToInsert)
    }


}
package bit.hemant.git.trends.feature_repo.domain.usecase

import bit.hemant.git.trends.feature_repo.data.repository.FakeRepoLocalRepository
import bit.hemant.git.trends.feature_repo.domain.model.Repo
import bit.hemant.git.trends.feature_repo.domain.repository.GitLocalRepository
import bit.hemant.git.trends.feature_repo.domain.util.AsyncResult
import bit.hemant.git.trends.feature_repo.domain.util.RepoOrder
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetLocalRepoUseCaseTest {


    private lateinit var localRepository: GitLocalRepository
    private lateinit var localReposUseCase: GetLocalReposUseCase


    @Before
    fun setUp() {
        localRepository = FakeRepoLocalRepository()
        localReposUseCase= GetLocalReposUseCase(localRepository)

        val repoList = mutableListOf<Repo>()
        ('a'..'z').forEachIndexed { index, c ->
            val dummyText = c.toString()
            repoList.add(
                Repo(
                    id = index,
                    name = dummyText,
                    language = dummyText,
                    forkCount = index,
                    starCount = index,
                    repoImage = "",
                    owner = dummyText
                )
            )
        }
        repoList.shuffle()
        runBlocking {
            localRepository.insertRepos(repoList)
        }

    }

    @Test
    fun `Order Repo by title, correct order`() = runBlocking {
        localReposUseCase(RepoOrder.Title).collect {
            when (it) {
                is AsyncResult.Success -> {
                    val repos = it.data
                    for (i in 0..repos.size - 2) {
                        assertThat(repos[i].name).isLessThan(repos[i + 1].name)
                    }
                }
            }
        }

    }

    @Test
    fun `Order Repo by star, correct order`() = runBlocking {
        localReposUseCase(RepoOrder.Star).collect {
            when (it) {
                is AsyncResult.Success -> {
                    val repos = it.data
                    for (i in 0..repos.size - 2) {
                        assertThat(repos[i].starCount).isLessThan(repos[i + 1].starCount)
                    }
                }
            }
        }
    }

}
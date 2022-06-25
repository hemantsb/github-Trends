package bit.hemant.git.trends.feature_repo.domain.usecase

import bit.hemant.git.trends.feature_repo.data.repository.FakeRepoLocalRepository
import bit.hemant.git.trends.feature_repo.data.repository.FakeRepoRemoteRepository
import bit.hemant.git.trends.feature_repo.domain.repository.GitLocalRepository
import bit.hemant.git.trends.feature_repo.domain.repository.GitRemoteRepository
import bit.hemant.git.trends.feature_repo.domain.util.AsyncResult
import bit.hemant.git.trends.feature_repo.domain.util.RepoOrder
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetReposUseCaseTest {


    private lateinit var getReposUseCase: GetRepoUseCase
    private lateinit var localRepository: GitLocalRepository
    private lateinit var remoteRepository: GitRemoteRepository


    @Before
    fun setUp() {
        localRepository = FakeRepoLocalRepository()
        remoteRepository = FakeRepoRemoteRepository()
        getReposUseCase = GetRepoUseCase(remoteRepository, localRepository)
    }

    @Test
    fun `Order Repo by title, correct order`() = runBlocking {
        getReposUseCase(RepoOrder.Title).collect {
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
        getReposUseCase(RepoOrder.Star).collect {
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
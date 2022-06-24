package bit.hemant.git.trends.feature_repo.data.data_source.local

import androidx.room.*
import bit.hemant.git.trends.feature_repo.domain.model.Repo
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {

    @Query("SELECT * from repo")
    suspend fun getAllRepo(): List<Repo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repos: List<Repo>)

    @Delete
    suspend fun delete(repo: Repo)
}
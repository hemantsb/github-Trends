package bit.hemant.git.trends.feature_repo.data.data_source.local

import androidx.room.Database
import bit.hemant.git.trends.feature_repo.domain.model.Repo


@Database(entities = [Repo::class], version = 1)
abstract class RepoDatabase {

    abstract fun repoDao(): RepoDao


    companion object {
        const val DATABASE_NAME = "Git Repo"
    }
}
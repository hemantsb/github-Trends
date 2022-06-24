package bit.hemant.git.trends.feature_repo.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import bit.hemant.git.trends.feature_repo.domain.model.Repo


@Database(entities = [Repo::class], version = 1)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDao


    companion object {
        const val DATABASE_NAME = "Git Repo"
    }
}
package bit.hemant.git.trends.feature_repo.domain.model

import androidx.room.PrimaryKey

class Repo(
    val name: String,
    val owner: String,
    val starCount: Int,
    val forkCount: Int,
    val language: String,
    @PrimaryKey val id: Int? = null
)
package bit.hemant.git.trends.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import bit.hemant.git.trends.BuildConfig
import bit.hemant.git.trends.feature_repo.data.data_source.local.RepoDatabase
import bit.hemant.git.trends.feature_repo.data.data_source.remote.GitRemoteDataSource
import bit.hemant.git.trends.feature_repo.data.data_source.remote.GitRemoteDataSourceImpl
import bit.hemant.git.trends.feature_repo.data.data_source.remote.GitRemoteService
import bit.hemant.git.trends.feature_repo.data.repository.GitLocalRepositoryImpl
import bit.hemant.git.trends.feature_repo.data.repository.GitRemoteRepositoryImpl
import bit.hemant.git.trends.feature_repo.domain.repository.GitLocalRepository
import bit.hemant.git.trends.feature_repo.domain.repository.GitRemoteRepository
import bit.hemant.git.trends.feature_repo.domain.usecase.GetReposUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }


    @Provides
    @Singleton
    fun providesDatabase(app: Application): RepoDatabase {
        return Room.databaseBuilder(app, RepoDatabase::class.java, RepoDatabase.DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun providesRepoLocalRepository(db: RepoDatabase): GitLocalRepository {
        return GitLocalRepositoryImpl(db.repoDao())
    }

    @Provides
    @Singleton
    fun providesGitRemoteDataSourceRepository(service: GitRemoteService): GitRemoteDataSource {
        return GitRemoteDataSourceImpl(service)
    }


    @Provides
    @Singleton
    fun providesRepoRemoteRepository(remoteDataSource: GitRemoteDataSource): GitRemoteRepository {
        return GitRemoteRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun providesRepoUseCase(
        remote: GitRemoteRepository,
        local: GitLocalRepository
    ): GetReposUseCase {
        return GetReposUseCase(
            remote = remote, local = local
        )
    }
}
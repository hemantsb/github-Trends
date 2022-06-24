package bit.hemant.git.trends.di

import bit.hemant.git.trends.feature_repo.data.data_source.remote.GitRemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun gitRemoteServiceProvider(retrofit: Retrofit): GitRemoteService {
        return retrofit.create(GitRemoteService::class.java)
    }

}
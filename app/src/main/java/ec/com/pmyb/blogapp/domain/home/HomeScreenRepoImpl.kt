package ec.com.pmyb.blogapp.domain.home

import ec.com.pmyb.blogapp.core.Result
import ec.com.pmyb.blogapp.data.model.Post
import ec.com.pmyb.blogapp.data.remote.home.HomeScreenDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource): HomeScreenRepo {

    @ExperimentalCoroutinesApi
    override suspend fun geLatesPosts(): Flow<Result<List<Post>>> = dataSource.getLatestPosts()
}
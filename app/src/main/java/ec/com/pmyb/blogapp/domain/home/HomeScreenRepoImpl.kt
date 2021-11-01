package ec.com.pmyb.blogapp.domain.home

import ec.com.pmyb.blogapp.core.Resource
import ec.com.pmyb.blogapp.data.model.Post
import ec.com.pmyb.blogapp.data.remote.home.HomeScreenDataSource

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource): HomeScreenRepo {

    override suspend fun geLatesPosts(): Resource<List<Post>> = dataSource.getLatestPosts()
}
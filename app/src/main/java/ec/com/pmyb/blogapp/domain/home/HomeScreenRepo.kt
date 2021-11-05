package ec.com.pmyb.blogapp.domain.home

import ec.com.pmyb.blogapp.core.Result
import ec.com.pmyb.blogapp.data.model.Post
import kotlinx.coroutines.flow.Flow

interface HomeScreenRepo {
    suspend fun geLatesPosts(): Flow<Result<List<Post>>>

}
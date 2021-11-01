package ec.com.pmyb.blogapp.domain.home

import ec.com.pmyb.blogapp.core.Result
import ec.com.pmyb.blogapp.data.model.Post

interface HomeScreenRepo {
    suspend fun geLatesPosts(): Result<List<Post>>

}
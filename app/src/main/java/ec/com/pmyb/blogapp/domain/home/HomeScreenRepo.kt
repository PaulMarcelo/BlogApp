package ec.com.pmyb.blogapp.domain.home

import ec.com.pmyb.blogapp.core.Resource
import ec.com.pmyb.blogapp.data.model.Post

interface HomeScreenRepo {
    suspend fun geLatesPosts(): Resource<List<Post>>

}
package ec.com.pmyb.blogapp.data.remote.home

import com.google.firebase.firestore.FirebaseFirestore
import ec.com.pmyb.blogapp.core.Resource
import ec.com.pmyb.blogapp.data.model.Post
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {

    suspend fun getLatestPosts(): Resource<List<Post>>{
        val postList = mutableListOf<Post>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("posts").get().await()
        for (post in querySnapshot.documents){
            post.toObject(Post::class.java)?.let {postIt ->
                postList.add(postIt)
            }
        }
        return Resource.Success(postList)

    }
}
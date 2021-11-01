package ec.com.pmyb.blogapp.data.model

import com.google.firebase.Timestamp

data class Post(val profile_picture: String = "",
                val profile_name: String = "",
                var post_timestamp: Timestamp? = null,
                val post_image: String = "")
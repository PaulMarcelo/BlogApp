package ec.com.pmyb.blogapp.domain.auth

import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseUser
import ec.com.pmyb.blogapp.data.remote.auth.AuthDataSource

class AuthRepoImpl(private val dataSource: AuthDataSource) : AuthRepo {
    override suspend fun signIn(email: String, password: String): FirebaseUser? =
        this.dataSource.signIn(email, password)

    override suspend fun signUp(email: String, password: String, username: String): FirebaseUser? =
        this.dataSource.signUp(email, password, username)

    //override suspend fun updateProfile(imageBitmap: Bitmap, username: String) =
        //dataSource.updateUserProfile(imageBitmap, username)
}
package ec.com.pmyb.blogapp.domain.auth

import com.google.firebase.auth.FirebaseUser
import ec.com.pmyb.blogapp.data.remote.auth.LoginDataSource
import ec.com.pmyb.blogapp.data.remote.home.HomeScreenDataSource

class LoginRepoImpl(private val dataSource: LoginDataSource) : LoginRepo {
    override suspend fun signIn(email: String, password: String): FirebaseUser? =
        this.dataSource.signIn(email, password)
}
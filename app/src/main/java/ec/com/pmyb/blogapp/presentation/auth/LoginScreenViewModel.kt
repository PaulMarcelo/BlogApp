package ec.com.pmyb.blogapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import ec.com.pmyb.blogapp.core.Resource
import ec.com.pmyb.blogapp.domain.auth.LoginRepo
import ec.com.pmyb.blogapp.domain.home.HomeScreenRepo
import kotlinx.coroutines.Dispatchers

class LoginScreenViewModel(private val repo: LoginRepo) : ViewModel() {
    fun signIn(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.signIn(email, password)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class LoginScreenViewModelFactory(private val repo: LoginRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginScreenViewModel(repo) as T
    }
}
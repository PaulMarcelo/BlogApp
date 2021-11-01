package ec.com.pmyb.blogapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import ec.com.pmyb.blogapp.core.Resource
import ec.com.pmyb.blogapp.domain.home.HomeScreenRepo
import kotlinx.coroutines.Dispatchers

class HomeScreenViewModel(private val repo: HomeScreenRepo) : ViewModel() {
    fun fetchLatesPosts() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            //emit(Resource.Success(repo.geLatesPosts()))
            emit(repo.geLatesPosts())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class HomeScreenViewModelFactory(private val repo: HomeScreenRepo):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeScreenRepo::class.java).newInstance(repo)
    }
}
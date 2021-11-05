package ec.com.pmyb.blogapp.presentation

import androidx.lifecycle.*
import ec.com.pmyb.blogapp.domain.home.HomeScreenRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.lang.Exception
import ec.com.pmyb.blogapp.core.Result

class HomeScreenViewModel(private val repo: HomeScreenRepo) : ViewModel() {

    fun fetchLatestPosts() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Result.Loading())
        kotlin.runCatching {
            repo.geLatesPosts()
        }.onSuccess { flowList ->
            flowList.collect {
                emit(it)
            }
        }.onFailure { throwable ->
            emit(Result.Failure(Exception(throwable.message)))
        }
    }
}

class HomeScreenViewModelFactory(private val repo: HomeScreenRepo):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeScreenRepo::class.java).newInstance(repo)
    }
}
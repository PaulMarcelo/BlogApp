package ec.com.pmyb.blogapp.presentation.camera

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import ec.com.pmyb.blogapp.domain.camera.CameraRepo
import kotlinx.coroutines.Dispatchers
import ec.com.pmyb.blogapp.core.Result

class CameraViewModel(private val repo: CameraRepo): ViewModel() {

    fun uploadPhoto(imageBitmap: Bitmap, description: String) =
        liveData(Dispatchers.IO) {
        //liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Result.Loading())
        try {
            emit(Result.Success(repo.uploadPhoto(imageBitmap,description)))
        }catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }
}

class CameraViewModelFactory(private val repo: CameraRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CameraRepo::class.java).newInstance(repo)
    }
}
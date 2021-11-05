package ec.com.pmyb.blogapp.domain.camera

import android.graphics.Bitmap

interface CameraRepo {
    suspend fun uploadPhoto(imageBitmap: Bitmap, description: String)
}
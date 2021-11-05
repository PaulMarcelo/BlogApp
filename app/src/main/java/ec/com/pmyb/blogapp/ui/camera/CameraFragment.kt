package ec.com.pmyb.blogapp.ui.camera

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import android.widget.Toast
import androidx.fragment.app.viewModels
import ec.com.pmyb.blogapp.R
import ec.com.pmyb.blogapp.core.Result
import ec.com.pmyb.blogapp.data.remote.camera.CameraDataSource
import ec.com.pmyb.blogapp.databinding.FragmentCameraBinding
import ec.com.pmyb.blogapp.domain.camera.CameraRepoImpl
import ec.com.pmyb.blogapp.presentation.camera.CameraViewModel
import ec.com.pmyb.blogapp.presentation.camera.CameraViewModelFactory

class CameraFragment : Fragment(R.layout.fragment_camera) {

    private val REQUEST_IMAGE_CAPTURE = 2
    private var bitmap: Bitmap? = null
    private lateinit var binding: FragmentCameraBinding
    private val viewModel by viewModels<CameraViewModel> {
        CameraViewModelFactory(
            CameraRepoImpl(
                CameraDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCameraBinding.bind(view)
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                "No se encontro app para abir la camara",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.btnUploadPhoto.setOnClickListener {
            bitmap?.let {
                viewModel.uploadPhoto(it, binding.etxtDescription.text.toString().trim()).observe(viewLifecycleOwner, { result ->
                    when (result) {
                        is Result.Loading -> {
                            Toast.makeText(requireContext(), "Uploading photo...", Toast.LENGTH_SHORT).show()
                        }
                        is Result.Success -> {
                            findNavController().navigate(R.id.action_cameraFragment_to_homeScreenFragment)
                        }
                        is Result.Failure -> {
                            Toast.makeText(requireContext(), "Error ${result.exception}", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.postImage.setImageBitmap(imageBitmap)
            bitmap = imageBitmap
        }
    }

}
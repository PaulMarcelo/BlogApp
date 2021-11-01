package ec.com.pmyb.blogapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import ec.com.pmyb.blogapp.R
import ec.com.pmyb.blogapp.data.remote.home.HomeScreenDataSource
import ec.com.pmyb.blogapp.databinding.FragmentHomeScreenBinding
import ec.com.pmyb.blogapp.domain.home.HomeScreenRepoImpl
import ec.com.pmyb.blogapp.presentation.HomeScreenViewModel
import ec.com.pmyb.blogapp.presentation.HomeScreenViewModelFactory
import ec.com.pmyb.blogapp.ui.home.adapter.HomeScreenAdapter
import ec.com.pmyb.blogapp.core.Result


class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding
    private val viewModel by viewModels<HomeScreenViewModel> {
        HomeScreenViewModelFactory(
            HomeScreenRepoImpl(
                HomeScreenDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)

        viewModel.fetchLatesPosts().observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvHome.adapter = HomeScreenAdapter(result.data)
                }
                is Result.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error ${result.exception}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

}
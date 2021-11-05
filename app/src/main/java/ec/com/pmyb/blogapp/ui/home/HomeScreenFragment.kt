package ec.com.pmyb.blogapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ec.com.pmyb.blogapp.R
import ec.com.pmyb.blogapp.data.remote.home.HomeScreenDataSource
import ec.com.pmyb.blogapp.databinding.FragmentHomeScreenBinding
import ec.com.pmyb.blogapp.domain.home.HomeScreenRepoImpl
import ec.com.pmyb.blogapp.presentation.HomeScreenViewModel
import ec.com.pmyb.blogapp.presentation.HomeScreenViewModelFactory
import ec.com.pmyb.blogapp.ui.home.adapter.HomeScreenAdapter
import ec.com.pmyb.blogapp.core.Result
import ec.com.pmyb.blogapp.core.hide
import ec.com.pmyb.blogapp.core.show
import ec.com.pmyb.blogapp.data.model.Post
import ec.com.pmyb.blogapp.ui.home.adapter.OnPostClickListener


class HomeScreenFragment : Fragment(R.layout.fragment_home_screen), OnPostClickListener {

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

        viewModel.fetchLatestPosts().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.hide()
                    if(result.data.isEmpty()) {
                        binding.emptyContainer.show()
                        return@Observer
                    }else{
                        binding.emptyContainer.hide()
                    }
                    binding.rvHome.adapter = HomeScreenAdapter(result.data, this)
                }
                is Result.Failure -> {
                    binding.progressBar.hide()
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error ${result.exception}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    override fun onLikeButtonClick(post: Post, liked: Boolean) {

    }

}
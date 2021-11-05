package ec.com.pmyb.blogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.firestore.FirebaseFirestore
import ec.com.pmyb.blogapp.core.hide
import ec.com.pmyb.blogapp.core.show
import ec.com.pmyb.blogapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        observerDestinationChange()
    }

    private fun observerDestinationChange() {
        navController.addOnDestinationChangedListener { _, destination, _  ->
            when (destination.id) {
                R.id.loginFragment -> {
                    binding.bottomNavigationView.hide()
                }
                R.id.registerFragment -> {
                    binding.bottomNavigationView.hide()
                }
                R.id.setupProfileFragment -> {
                    binding.bottomNavigationView.hide()
                }
                else -> {
                    binding.bottomNavigationView.show()
                }
            }
        }
    }

}
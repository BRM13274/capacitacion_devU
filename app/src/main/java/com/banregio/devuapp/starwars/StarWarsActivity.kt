package com.banregio.devuapp.starwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.banregio.devuapp.R
import com.banregio.devuapp.databinding.ActivityStarWarsBinding

class StarWarsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStarWarsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStarWarsBinding.inflate(layoutInflater)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_sw) as NavHostFragment?

        navHostFragment?.let {
            val navController = it.navController
            val navGraph = navController.navInflater.inflate(R.navigation.star_wars_navigation)

            navController.graph = navGraph

            // Sincronizando el ActionBar con el NavController cuando aplique.
            supportActionBar?.let {
                NavigationUI.setupActionBarWithNavController(this, navController)
            }
        } ?: throw IllegalStateException()
    }

    fun showLoading() {

    }

}
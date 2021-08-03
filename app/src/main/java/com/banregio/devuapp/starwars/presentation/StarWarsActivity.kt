package com.banregio.devuapp.starwars.presentation

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.banregio.devuapp.R
import com.banregio.devuapp.databinding.ActivityStarWarsBinding

class StarWarsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStarWarsBinding
    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStarWarsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()

        loadingDialog = Dialog(this)
        loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        loadingDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loadingDialog.setContentView(R.layout.loading_layout)
        loadingDialog.setCancelable(false)

    }

    override fun onDestroy() {
        dismissLoading()
        super.onDestroy()
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
        if (!isFinishing) {
            loadingDialog.show()
        }
    }

    fun dismissLoading() {
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }

}
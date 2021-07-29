package com.banregio.devuapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.banregio.devuapp.starwars.StarWarsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            //Los snackBars son componentes visuales como el Toast pero pueden contener acciones
            Snackbar.make(
                view,
                "Botón flotante que vive en el activity (nivel más alto)",
                Snackbar.LENGTH_LONG
            )
                .setAction("Acción", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        //Se envían los IDs al componente de control lateral, pero pertenecen a destinos en la navegación
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener { item ->
            processItemId(item.itemId)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun processItemId(menuItemId: Int): Boolean {
        if (menuItemId == R.id.nav_star_wars) {
            goToStarWars()
            return true
        }
        return false
    }


    /**
     * Esta función contiene el código necesario para viajar de un activity a otro.
     * Intenta conectar esta función detectando el "click" del menú.
     * Puedes guiarte del código en la presentación.
     * */
    private fun navigateToDifferentActivity() {
        val intent = Intent(this, SecondaryActivity::class.java)
        startActivity(intent)
    }

    /**
     * Dispara el flujo de star wars.
     * */
    private fun goToStarWars() {
        val intent = Intent(this, StarWarsActivity::class.java)
        startActivity(intent)
    }


}
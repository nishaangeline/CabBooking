package com.example.cabapp

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.cabapp.databinding.ActivityMainBinding
import com.example.cabapp.ui.BaseFragment

class MainActivity : AppCompatActivity() {

    private var activityMainBinding: ActivityMainBinding? = null
    private val binding get() = activityMainBinding!!
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setSupportActionBar(binding.toolbar)

        navController = (supportFragmentManager
            .findFragmentById(binding.navHostFragmentContentMain.id) as NavHostFragment? ?: return).navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragment_login, R.id.fragment_admin, R.id.fragment_home
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onBackPressed() {
        if (isTopLevelDestination()) {
            if (navController.currentDestination?.id == R.id.fragment_login && navController.previousBackStackEntry == null) {
                onSupportNavigateUp()
            }
            finish()
        } else {
            onSupportNavigateUp()
        }
    }

    private fun isTopLevelDestination(): Boolean {
        return appBarConfiguration.topLevelDestinations.contains(navController.currentDestination?.id)
    }

    override fun onSupportNavigateUp(): Boolean {
        val fragment = getCurrentDestinationFragment()
        if (!isTopLevelDestination() && fragment != null && fragment.onBackPressed()) {
            return true
        }
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    private fun getCurrentDestinationFragment(): BaseFragment? {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)?.childFragmentManager?.fragments?.get(0) as? BaseFragment?
    }

    override fun onDestroy() {
        super.onDestroy()
        activityMainBinding = null
    }
}
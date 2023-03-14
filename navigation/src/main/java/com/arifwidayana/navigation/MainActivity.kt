package com.arifwidayana.navigation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.arifwidayana.navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.apply {
//            override fun onNavigationItemSelected(item: MenuItem): Boolean {
//                if (navController.graph.findNode(item.itemId) != null) {
//                    navController.graph.startDestination = item.itemId // <- The Magic Sauce
//
//                    val options = with(NavOptions.Builder()) {
//                        setPopUpTo(R.navigation.my_nav_graph, true)
//                        build()
//                    }
//
//                    navController.navigate(item.itemId, null, options)
//                    val parent = navigationView.parent
//                    if (parent is DrawerLayout) {
//                        parent.closeDrawer(navigationView)
//                    }
//                    return true
//                }
//
//                return onNoDestination.invoke(item)
//            }
//            val navController = findNavController(R.id.fragmentContainerView)
//            bottomNavigationView.setupWithNavController(navController)
//            navController.addOnDestinationChangedListener { _, destination, _ ->
//                when (destination.id) {
//                    com.arifwidayana.home.R.id.homeFragment,
//                    R.id.notificationFragment,
//                    R.id.sellFragment,
//                    R.id.saleFragment,
//                    com.arifwidayana.account.R.id.accountFragment -> {
//                        bottomNavigationView.visibility = View.VISIBLE
//                    }
//                    else -> {
//                        bottomNavigationView.visibility = View.GONE
//                    }
//                }
//            }
            val navController = findNavController(R.id.fragmentContainerView)
            bottomNavigationView.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                bottomNavigationView.visibility = when (destination.id) {
                    com.arifwidayana.home.R.id.homeFragment,
                    R.id.notificationFragment,
                    R.id.sellFragment,
                    R.id.saleFragment,
                    com.arifwidayana.account.R.id.accountFragment -> View.VISIBLE
                    else -> View.GONE
                }
            }
        }
    }
}
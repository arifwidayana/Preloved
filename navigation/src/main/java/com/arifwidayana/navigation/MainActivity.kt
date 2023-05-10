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
            val navController = findNavController(R.id.fragmentContainerView)
            bottomNavigationView.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                bottomNavigationView.visibility = when (destination.id) {
                    com.arifwidayana.home.R.id.homeFragment,
                    com.arifwidayana.notification.R.id.notificationFragment,
                    com.arifwidayana.sell.R.id.sellFragment,
                    com.arifwidayana.sale.R.id.saleFragment,
                    com.arifwidayana.account.R.id.accountFragment -> View.VISIBLE
                    else -> View.GONE
                }
            }
        }
    }
}
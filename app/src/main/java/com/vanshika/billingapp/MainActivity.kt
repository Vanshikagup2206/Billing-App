package com.vanshika.billingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.vanshika.billingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding : ActivityMainBinding ?= null
    var navController : NavController ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        navController = findNavController(R.id.host)
        binding?.bottomNavBar?.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottomNavItems -> navController?.navigate(R.id.itemsFragment)
                R.id.bottomNavBills -> navController?.navigate(R.id.billsFragment)
            }
            return@setOnItemSelectedListener true
        }
        navController?.addOnDestinationChangedListener{navController,destination,argument ->
            when(destination.id){
                R.id.itemsFragment -> {
                    supportActionBar?.title = resources.getString(R.string.items)
                    binding?.bottomNavBar?.menu?.findItem(R.id.bottomNavItems)?.isChecked = true
                }
                R.id.billsFragment -> {
                    supportActionBar?.title = resources.getString(R.string.bills)
                    binding?.bottomNavBar?.menu?.findItem(R.id.bottomNavBills)?.isChecked = true
                }
            }
        }
    }
}
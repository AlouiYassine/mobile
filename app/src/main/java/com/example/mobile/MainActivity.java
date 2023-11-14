package com.example.mobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import appdatabase.CommandeDatabase
import entities.commande


class MainActivity : AppCompatActivity() {
private var binding: ActivityMainBinding? = null
private var commandeListAdapter: CommandeListAdapter? = null
private var commandeDao: Dao.CommandeDao? = null
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        initializeDatabases()




        // Fetch Commandes and update UI
        fetchCommandes()

        // ... rest of your code
        binding.fabAdd.setOnClickListener { view -> navigateToAddInterface() }
        }

private fun initializeDatabases() {
        val commandeDatabase = CommandeDatabase.getInstance(this)
        commandeDao = commandeDatabase.commandeDao()
        }


private fun fetchCommandes() {
        Thread {
        val commandes: ArrayList<commande> =
        ArrayList<Any?>(commandeDao.getAllCommandes())
        runOnUiThread { updateCommandesUI(commandes) }
        }.start()
        }



private fun updateCommandesUI(commandes: ArrayList<commande>) {
        commandeListAdapter = CommandeListAdapter(this, commandes)
        // Set up your UI components for displaying commandes
        // For example, you might have a different ListView or RecyclerView for commandes
        // Update the adapter and item click listener accordingly
        }

private fun navigateToAddInterface() {
        // Replace AddInterfaceActivity.class with the actual class for your add interface
        val intent = Intent(this@MainActivity, AddCommandeActivity::class.java)
        startActivity(intent)
        finish()
        }
        }

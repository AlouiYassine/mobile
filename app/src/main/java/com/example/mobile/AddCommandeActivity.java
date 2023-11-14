package com.example.mobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import appdatabase.CommandeDatabase;
import entities.commande;

public class AddCommandeActivity extends AppCompatActivity
{
    private dao.CommandeDao commandeDao;

    private void initializeDatabase() {
        commandeDao = CommandeDatabase.getInstance(this).commandeDao();
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commande);
        initializeDatabase();
        // Find views by their IDs
        TextView textViewNom = findViewById(R.id.textViewNom);
        EditText editTextNom = findViewById(R.id.editTextNom);

        TextView textViewPrenom = findViewById(R.id.textViewPrenom);
        EditText editTextPrenom = findViewById(R.id.editTextPrenom);

        TextView textViewAdresse = findViewById(R.id.textViewAdresse);
        EditText editTextAdresse = findViewById(R.id.editTextAdresse);

        TextView textViewNumero = findViewById(R.id.textViewNumero);
        EditText editTextNumero = findViewById(R.id.editTextNumero);

        Button buttonSave = findViewById(R.id.buttonSave);

        // Now you can use these views as needed in your code
        // For example, you can set an OnClickListener for the button
        buttonSave.setOnClickListener(view -> {
            // Perform actions when the button is clicked
            String nom = editTextNom.getText().toString();
            String prenom = editTextPrenom.getText().toString();
            String adresse = editTextAdresse.getText().toString();
            String numero = editTextNumero.getText().toString();

            // Create a new Commande object
            commande newCommande = new commande(nom, prenom, adresse, numero);

// Insert the new Commande into your Room database
            new Thread(new Runnable() {
                @Override
                public void run() {
                    commandeDao.insertCommande(newCommande);
                }
            }).start();

// Assuming you want to navigate to another activity after saving the Commande
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

            finish();
        });
    }
}

package com.example.mobile;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import appdatabase.CommandeDatabase;

public class DetailedActivity extends AppCompatActivity {
    private dao.CommandeDao commandeDao;

    ActivityDetailedBinding binding;
    private Button deleteButton;

    private void initializeDatabase() {
        commandeDao = CommandeDatabase.getInstance(this).commandeDao();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommandeDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeDatabase();

        Intent intent = this.getIntent();
        if (intent != null) {
            String nom = intent.getStringExtra("nom");
            String prenom = intent.getStringExtra("prenom");
            String adresse = intent.getStringExtra("adresse");
            int numero = intent.getIntExtra("numero", -1);
            int id = intent.getIntExtra("id", -1);

            binding.detailId.setText(String.valueOf(id));
            binding.detailName.setText("Commande Details");
            binding.detailTime.setText("time");  // You need to set the actual time from your intent
            binding.detailIngredients.setText(getString(R.string.maggiIngredients));  // Set the actual ingredients from your intent

            binding.updateButton.setOnClickListener(view -> {
                Intent updateIntent = new Intent(CommandeDetailedActivity.this, UpdateCommandeActivity.class);
                updateIntent.putExtra("nom", nom);
                updateIntent.putExtra("prenom", prenom);
                updateIntent.putExtra("adresse", adresse);
                updateIntent.putExtra("numero", numero);
                updateIntent.putExtra("id", id);
                startActivity(updateIntent);
                finish();
            });
        }

        deleteButton = findViewById(R.id.commandeDeleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog();
            }
        });

        binding.commandeUpdateButton.setOnClickListener(view -> {
            Intent updateIntent = new Intent(CommandeDetailedActivity.this, UpdateCommandeActivity.class);
            updateIntent.putExtra("nom", binding.commandeDetailNom.getText().toString());
            updateIntent.putExtra("prenom", binding.commandeDetailPrenom.getText().toString());
            updateIntent.putExtra("adresse", binding.commandeDetailAdresse.getText().toString());
            updateIntent.putExtra("numero", Integer.parseInt(binding.commandeDetailNumero.getText().toString()));
            updateIntent.putExtra("id", Integer.parseInt(binding.commandeDetailId.getText().toString()));

            startActivity(updateIntent);
            finish();
        });
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Commande");
        builder.setMessage("Are you sure you want to delete this commande?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteCommande();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    private void deleteCommande() {
        String idString = binding.commandeDetailId.getText().toString();

        if (!idString.isEmpty()) {
            int commandeId = Integer.parseInt(idString);

            new Thread(() -> {
                commandeDao.deleteCommandeById(commandeId);

                runOnUiThread(() -> {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                });
            }).start();
        } else {
            // Handle the case where the id is empty or invalid
        }
    }
}


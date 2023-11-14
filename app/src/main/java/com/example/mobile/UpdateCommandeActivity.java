import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile.MainActivity;
import com.example.mobile.R;

import appdatabase.CommandeDatabase;
import dao.CommandeDao;

import entities.commande;

public class UpdateCommandeActivity extends AppCompatActivity {

    private CommandeDao commandeDao;
    private commande existingCommande;

    private EditText editId;
    private EditText editNom;
    private EditText editPrenom;
    private EditText editAdresse;
    private EditText editNumero;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatecommandeactivity);

        initializeDatabase();

        // Find views using their IDs
        editId = findViewById(R.id.editId);
        editNom = findViewById(R.id.editNom);
        editPrenom = findViewById(R.id.editPrenom);
        editAdresse = findViewById(R.id.editAdresse);
        editNumero = findViewById(R.id.editNumero);
        updateButton = findViewById(R.id.updateButton);

        // Fetch the existing Commande data from the Intent
        Intent intent = getIntent();
        String nom = intent.getStringExtra("nom");
        String prenom = intent.getStringExtra("prenom");
        String adresse = intent.getStringExtra("adresse");
        String numero = intent.getStringExtra("numero");
        String id = intent.getStringExtra("id");

        // Populate the EditText fields with existing data
        editId.setText(id);
        editNom.setText(nom);
        editPrenom.setText(prenom);
        editAdresse.setText(adresse);
        editNumero.setText(numero);

        // Set click listener for the update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCommande();
            }
        });
    }

    private void initializeDatabase() {
        commandeDao = CommandeDatabase.getInstance(this).commandeDao();
    }

    private void updateCommande() {
        // Get updated data from UI elements
        String updatedNom = editNom.getText().toString();
        String updatedPrenom = editPrenom.getText().toString();
        String updatedAdresse = editAdresse.getText().toString();
        String updatedNumero = editNumero.getText().toString();
        String id = editId.getText().toString();

        // Perform the update using the DAO on a separate thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Retrieve the existing Commande object from the database based on the provided data
                existingCommande = commandeDao.getCommandeById(Integer.parseInt(id));

                // Check if Commande is still null (not found in the database)
                if (existingCommande == null) {
                    finish();
                    return;
                }

                // Update the existing Commande object
                existingCommande.setNom(updatedNom);
                existingCommande.setPrenom(updatedPrenom);
                existingCommande.setAdresse(updatedAdresse);
                existingCommande.setNumero(Integer.parseInt(updatedNumero));

                // Update the Commande object in the database
                commandeDao.updateCommande(existingCommande);
            }
        }).start();

        // Provide feedback to the user
        Toast.makeText(this, "Commande updated successfully", Toast.LENGTH_SHORT).show();

        // Finish the activity
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}

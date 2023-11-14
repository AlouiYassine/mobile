package dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

import entities.commande;

@Dao
public interface CommandeDao {

    @Insert
    long insertCommande(commande Commande);

    @Update
    void updateCommande(commande Commande);

    @Delete
    void deleteCommande(commande Commande);

    @Query("SELECT * FROM commande")
    List<commande> getAllCommandes();

    @Query("SELECT * FROM commande WHERE id = :commandeId")
    commande getCommandeById(int commandeId);

    void deleteCommandeById(int commandeId);
}

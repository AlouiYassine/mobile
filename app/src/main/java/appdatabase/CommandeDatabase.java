package appdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import entities.commande;

@Database(entities = {commande.class}, version = 1)
public abstract class CommandeDatabase extends RoomDatabase {
    private static CommandeDatabase instance;

    public abstract dao.CommandeDao commandeDao();

    public static synchronized CommandeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            CommandeDatabase.class, "commande_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

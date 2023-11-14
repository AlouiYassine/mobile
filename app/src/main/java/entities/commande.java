package entities;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class commande {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nom;
    private String prenom;
    private String adresse;
    private String numero;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getNumero() {
        return numero;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public commande(String nom, String prenom, String adresse, String numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numero = Integer.parseInt(numero);
    }


    @Override
    public String toString() {
        return "commande{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", numero=" + numero +
                '}';
    }
}

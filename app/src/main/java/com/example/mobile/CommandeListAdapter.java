package com.example.mobile;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import entities.commande;

public class CommandeListAdapter extends ArrayAdapter<commande> {

    public CommandeListAdapter(@NonNull Context context, ArrayList<commande> dataArrayList) {
        super(context, R.layout.listcommande, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        commande commande = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.listcommande, parent, false);
        }

        // Find TextViews in the list_item layout
        TextView listNom = view.findViewById(R.id.listNom);
        TextView listPrenom = view.findViewById(R.id.listPrenom);
        TextView listAdresse = view.findViewById(R.id.listAdresse);
        TextView listNumero = view.findViewById(R.id.listNumero);

        // Set the values to the TextViews
        if (commande != null) {
            listNom.setText(commande.getNom());
            listPrenom.setText(commande.getPrenom());
            listAdresse.setText(commande.getAdresse());
            listNumero.setText(String.valueOf(commande.getNumero()));
        }

        return view;
    }
}

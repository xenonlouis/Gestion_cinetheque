package com.example.gestion_cinetheque.Gui_client;


import com.example.gestion_cinetheque.Models.CD;
import com.example.gestion_cinetheque.Models.Emprunt;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CinethequeClient extends JFrame {
    private JTextField titreField;
    private JTextField artisteField;
    private JList<CD> cdList;
    private DefaultListModel<CD> cdListModel;

    private List<CD> cds; // Liste pour simuler la base de données

    public CinethequeClient() {
        setTitle("Gestion de Cinetheque");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Liste des CDs
        cds = new ArrayList<>();
        cdListModel = new DefaultListModel<>();
        cdList = new JList<>(cdListModel);
        JScrollPane scrollPane = new JScrollPane(cdList);
        add(scrollPane, BorderLayout.CENTER);

        // Formulaire d'ajout de CD
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2));

        formPanel.add(new JLabel("Titre:"));
        titreField = new JTextField();
        formPanel.add(titreField);

        formPanel.add(new JLabel("Artiste:"));
        artisteField = new JTextField();
        formPanel.add(artisteField);

        JButton addButton = new JButton("Ajouter CD");
        addButton.addActionListener(e -> addCD());
        formPanel.add(addButton);

        JButton deleteButton = new JButton("Supprimer CD");
        deleteButton.addActionListener(e -> deleteCD());
        formPanel.add(deleteButton);

        add(formPanel, BorderLayout.SOUTH);
    }

    private void addCD() {
        String titre = titreField.getText();
        String artiste = artisteField.getText();

        if (!titre.isEmpty() && !artiste.isEmpty()) {
            CD cd = new CD(titre, artiste);
            cds.add(cd);
            cdListModel.addElement(cd);
            titreField.setText("");
            artisteField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs");
        }
    }

    private void deleteCD() {
        CD selectedCD = cdList.getSelectedValue();
        if (selectedCD != null) {
            cds.remove(selectedCD);
            cdListModel.removeElement(selectedCD);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un CD à supprimer");
        }
    }
    // Ajoutez une nouvelle méthode pour gérer les emprunts
    private void createEmpruntPanel() {
        JPanel empruntPanel = new JPanel();
        empruntPanel.setLayout(new BorderLayout());

        // Liste des emprunts
        JList<Emprunt> empruntList = new JList<>();
        DefaultListModel<Emprunt> empruntListModel = new DefaultListModel<>();
        empruntList.setModel(empruntListModel);
        JScrollPane scrollPaneEmprunts = new JScrollPane(empruntList);
        empruntPanel.add(scrollPaneEmprunts, BorderLayout.CENTER);

        // Formulaire d'emprunt
        JPanel empruntForm = new JPanel();
        empruntForm.setLayout(new GridLayout(3, 1));

        JButton emprunterButton = new JButton("Emprunter");
        emprunterButton.addActionListener(e -> {
            CD selectedCD = cdList.getSelectedValue();
            if (selectedCD != null) {
                Emprunt emprunt = new Emprunt(selectedCD, "user", LocalDate.now());
                empruntListModel.addElement(emprunt);
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un CD à emprunter");
            }
        });

        empruntForm.add(emprunterButton);
        empruntPanel.add(empruntForm, BorderLayout.SOUTH);
        add(empruntPanel, BorderLayout.EAST); // Ajoutez le panel d'emprunt à la fenêtre principale
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CinethequeClient client = new CinethequeClient();
            client.setVisible(true);
        });
    }
}

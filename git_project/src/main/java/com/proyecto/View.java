package com.proyecto;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class view {

    JFrame f;
    JTable j;

    view() {

        f = new JFrame();

        f.setTitle("IU FOR DUMMIES");

        String[][] data = {
                { "1", "Tom", "Cruise", "https://jsonformatter.org/img/tom-cruise.jpg" },
                { "2", "Maria", "Sharapova", "https://jsonformatter.org/img/Maria-Sharapova.jpg" },
                { "3", "Robert", "Downey Jr.", "https://jsonformatter.org/img/Robert-Downey-Jr.jpg" }
        };

        String[] columnNames = { "ID", "Firstname", "Last Name", "Photo" };

        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);

        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        f.setSize(500, 200);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new view();
    }
}

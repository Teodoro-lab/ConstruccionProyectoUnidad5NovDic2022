package com.proyecto.view;

import javax.swing.*;

import com.proyecto.Employee;
import com.proyecto.JsonManager;

public class AddEmployee extends JFrame {

    private JLabel labelID;
    private JLabel labelFirstName;
    private JLabel labelLastName;
    private JLabel labelPhoto;

    JsonManager jsonManager = new JsonManager();
    Employee[] info = jsonManager.convertJsonToList();
    JTextField textfieldID;
    JTextField textfieldFirstName;
    JTextField textfieldLastName;
    JTextField textfieldPhoto;

    public AddEmployee(EmployeeTableManager employeeTableManager) {

        setLayout(null);
        setBounds(0, 0, 400, 300);
        setTitle("Employee data");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        labelFirstName = new JLabel("ID:");
        labelFirstName.setBounds(40, 10, 100, 30);
        add(labelFirstName);

        textfieldID = employeeTableManager.getTextfieldID();
        textfieldID.setBounds(150, 10, 200, 20);
        add(textfieldID);

        labelID = new JLabel("First Name:");
        labelID.setBounds(40, 30, 140, 70);
        add(labelID);

        textfieldFirstName = employeeTableManager.getTextfieldFirstName();
        textfieldFirstName.setBounds(150, 54, 200, 20);
        add(textfieldFirstName);

        labelLastName = new JLabel("Last Name:");
        labelLastName.setBounds(40, 80, 170, 70);
        add(labelLastName);

        textfieldLastName = employeeTableManager.getTextfieldLasttName();
        textfieldLastName.setBounds(150, 100, 200, 20);
        add(textfieldLastName);

        labelPhoto = new JLabel("URL photo:");
        labelPhoto.setBounds(40, 120, 200, 70);
        add(labelPhoto);

        textfieldPhoto = employeeTableManager.getTextfieldPhoto();
        textfieldPhoto.setBounds(150, 150, 200, 20);
        add(textfieldPhoto);

        JButton button3 = employeeTableManager.getButton3();
        button3.setBounds(10, 200, 350, 30);
        add(button3);

        setVisible(true);
    }

    public void clearFields() {
        this.textfieldID.setText("");
        this.textfieldFirstName.setText("");
        this.textfieldLastName.setText("");
        this.textfieldPhoto.setText("");
    }

}
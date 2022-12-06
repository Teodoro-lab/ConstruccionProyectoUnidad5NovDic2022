package com.proyecto.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.proyecto.Employee;
import com.proyecto.JsonManager;
import java.awt.BorderLayout;

public class View {
    JFrame f;
    JsonManager jsonManager = new JsonManager();
    Employee[] info = jsonManager.convertJsonToList();

    public View() {

        f = new JFrame();
        f.setTitle("IU employees");

        EmployeeTableManager employeeTableManager = new EmployeeTableManager(info);
        JTable table = employeeTableManager.getTable();

        JButton button = employeeTableManager.getButton();

        table.setRowHeight(250);

        JScrollPane scrollPane = new JScrollPane(table);
        f.add(scrollPane);
        f.add(button, BorderLayout.SOUTH);

        f.setSize(900, 700);
        f.setVisible(true);
    }
}
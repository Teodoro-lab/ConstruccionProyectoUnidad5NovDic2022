package com.proyecto.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.proyecto.Employee;
import com.proyecto.JsonManager;

public class View {

    JFrame f;
    JsonManager jsonManager = new JsonManager();
    Employee[] info = jsonManager.convertJsonToList();

    public View() {

        f = new JFrame();
        f.setTitle("IU employees");

        EmployeeTableManager employeeTableManager = new EmployeeTableManager(info);
        JTable table = employeeTableManager.getTable();

        table.setRowHeight(250);

        JScrollPane scrollPane = new JScrollPane(table);
        f.add(scrollPane);

        f.setSize(500, 200);
        f.setVisible(true);
    }

}
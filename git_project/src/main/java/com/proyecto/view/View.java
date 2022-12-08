package com.proyecto.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.proyecto.Employee;
import com.proyecto.JsonManager;

import java.awt.*;

public class View {
    JFrame f;
    JsonManager jsonManager = new JsonManager();
    Employee[] info = jsonManager.convertJsonToList();

    public View() {

        f = new JFrame();
        f.setTitle("IU employees");

        EmployeeTableManager employeeTableManager = new EmployeeTableManager(info, f);
        JTable table = employeeTableManager.getTable();

        JButton button = employeeTableManager.getButton();

        table.setRowHeight(250);

        JScrollPane scrollPane = new JScrollPane(table);
        f.add(scrollPane);
        f.add(button, BorderLayout.NORTH);

        JPanel btnPnl = new JPanel(new BorderLayout());

        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button2 = employeeTableManager.getButton2();

        bottombtnPnl.add(button2);

        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);
        table.getTableHeader().setReorderingAllowed(false);

        f.add(btnPnl, BorderLayout.SOUTH);

        f.setTitle("IU employees.");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

        f.setSize(900, 700);

    }
}
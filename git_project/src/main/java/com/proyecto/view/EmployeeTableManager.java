package com.proyecto.view;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;

import java.util.Arrays;
import java.util.List;
import com.proyecto.Employee;
import com.proyecto.JsonManager;
import com.proyecto.view.DeleteButton.ButtonEditor;
import com.proyecto.view.DeleteButton.ButtonRenderer;

public class EmployeeTableManager {
    private JTable table;
    String[] columnNames = { "id", "firstName", "lastName", "photo", "delete" };
    JsonManager jsonManager = new JsonManager();
    List<Employee> info;

    public EmployeeTableManager(Employee[] infoEmployee) {
        this.info = Arrays.asList(infoEmployee);

        Object[][] data = getEmployeeData(infoEmployee);

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };

        this.table = new JTable(model);

        DeleteButton dbutton = new DeleteButton(table);

        table.getColumn("delete").setCellRenderer((TableCellRenderer) new ButtonRenderer());
        table.getColumn("delete").setCellEditor((TableCellEditor) new ButtonEditor(new JCheckBox()));

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableCellListener tcl = (TableCellListener) e.getSource();

                if (tcl.getOldValue() == tcl.getNewValue()) {
                    return;
                }

                String value = tcl.getNewValue().toString();
                updateEmployee(tcl.getRow(), tcl.getColumn(), value);

            }
        };

        TableCellListener tcl = new TableCellListener(table, action);
    }

    public JTable getTable() {
        return table;
    }

    private void updateEmployee(int row, int col, String value) {
        Employee employee = info.get(row);

        switch (col) {
            case 0:
                // employee.setId((int) tcl.getNewValue());
                break;
            case 1:
                employee.setFirstName(value);
                break;
            case 2:
                employee.setLastName(value);
                break;
            case 3:
                employee.setPhoto(value);
                break;
            default:
                break;
        }
        jsonManager.updateEmployeeInJson(employee);
    }

    private Icon getImgIcon(String urlStr) {

        try {
            URL url = new URL(urlStr);
            BufferedImage myPicture = ImageIO.read(url);
            return new ImageIcon(myPicture);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Object[][] getEmployeeData(Employee[] employees) {
        Object[][] employeesData = new Object[employees.length][];

        for (int i = 0; i < info.size(); i++) {
            employeesData[i] = employees[i].getEmployeeAsArray();
            employeesData[i][3] = getImgIcon((String) employeesData[i][3]);
        }

        return employeesData;
    }
}

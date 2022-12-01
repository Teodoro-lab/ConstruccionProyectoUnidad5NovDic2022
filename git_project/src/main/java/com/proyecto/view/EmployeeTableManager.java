package com.proyecto.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.proyecto.Employee;
import com.proyecto.JsonManager;

public class EmployeeTableManager {
    private JTable table;
    String[] columnNames = { "id", "firstName", "lastName", "photo" };
    JsonManager jsonManager = new JsonManager();
    Employee[] info;
    JButton button;

    public EmployeeTableManager(Employee[] info) {
        this.info = info;

        Object[][] data = getEmployeeData(info);

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            // Returning the Class of each column will allow different
            // renderers to be used based on Class
            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };

        this.table = new JTable(model);

        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        button = new JButton("Remove");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // check for selected row first
                if (table.getSelectedRow() != -1) {
                    // remove selected row from the model
                    model.removeRow(table.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                }
            }
        });

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
        Employee employee = info[row];

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
        for (int i = 0; i < employees.length; i++) {
            employeesData[i] = employees[i].getEmployeeAsArray();
            employeesData[i][3] = getImgIcon((String) employeesData[i][3]);
        }
        return employeesData;
    }

    public JButton getButton() {
        return button;
    }
}
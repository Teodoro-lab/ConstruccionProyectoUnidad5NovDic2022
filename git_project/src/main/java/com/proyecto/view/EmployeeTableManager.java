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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.proyecto.Employee;
import com.proyecto.JsonManager;

public class EmployeeTableManager {
    private JTable table;

    String[] columnNames = { "id", "firstName", "lastName", "photo" };
    JsonManager jsonManager = new JsonManager();
    Employee[] info;
    JButton button;
    JButton button2;
    JButton button3;

    private JTextField textfieldID;
    private JTextField textfieldFirstName;
    private JTextField textfieldLastName;
    private JTextField textfieldPhoto;
    JFrame newFrameTable;

    public DefaultTableModel createModelAdd(Employee[] info) {
        Object[][] data = getEmployeeData(info);
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {

            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        return model;

    }

    EmployeeTableManager myself = null;
    AddEmployee addEmployeeF = null;

    public EmployeeTableManager(Employee[] info, JFrame f) {
        this.info = info;
        this.newFrameTable = f;
        DefaultTableModel model = createModelAdd(info);

        this.table = new JTable(model);

        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        button = new JButton("Remove");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (table.getSelectedRow() != -1) {

                    int col = 0;
                    int row = table.getSelectedRow();

                    String employeeId = (String) table.getValueAt(row, col);
                    System.out.println(employeeId);

                    model.removeRow(table.getSelectedRow());

                    jsonManager.deleteEmployeeFromJson(employeeId);
                    JOptionPane.showMessageDialog(null, "Employee deleted successfully");
                }
            }
        });

        button2 = new JButton("ADD EMPLOYEE");
        myself = this;
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == button2) {
                    addEmployeeF = new AddEmployee(myself);

                    // JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                }
            }
        });

        textfieldID = new JTextField();
        textfieldFirstName = new JTextField();
        textfieldLastName = new JTextField();
        textfieldPhoto = new JTextField();

        button3 = new JButton("ADD ");

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == button3) {

                    Employee newEmployee = new Employee(textfieldID.getText(), textfieldFirstName.getText(),
                            textfieldLastName.getText(), textfieldPhoto.getText());

                    jsonManager.AddEmployeeFromJson(newEmployee);
                    Employee[] myNewEmployees = jsonManager.convertJsonToList();

                    DefaultTableModel newModel = createModelAdd(myNewEmployees);
                    table.setModel(newModel);
                    addEmployeeF.setVisible(false);
                    addEmployeeF.clearFields();
                    JOptionPane.showMessageDialog(null, "Employee added successfully");

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

    public JButton getButton2() {
        return button2;
    }

    public JButton getButton3() {
        return button3;
    }

    public JTextField getTextfieldID() {
        return textfieldID;
    }

    public JTextField getTextfieldFirstName() {
        return textfieldFirstName;
    }

    public JTextField getTextfieldLasttName() {
        return textfieldLastName;
    }

    public JTextField getTextfieldPhoto() {
        return textfieldPhoto;
    }

}
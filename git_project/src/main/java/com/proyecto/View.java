package com.proyecto;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class View {

    JFrame f;
    JTable j;

    View() {

        f = new JFrame();

        f.setTitle("IU FOR DUMMIES");

        JsonReader rj = new JsonReader();
        Employee[] info = rj.convertJsonToList();

        Object[][] data = getEmployeeData(info);
        String[] columnNames = { "id", "firstName", "lastName", "photo" };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            // Returning the Class of each column will allow different
            // renderers to be used based on Class
            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };

        if (info.length > 0) {
            JTable table = new JTable(model);
            // table.setPreferredScrollableViewportSize(table.getPreferredSize());
            // table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
            table.setRowHeight(250);

            JScrollPane scrollPane = new JScrollPane(table);
            f.add(scrollPane);
        }

        f.setSize(500, 200);
        f.setVisible(true);
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

    public static void main(String[] args) {
        new View();
    }
}
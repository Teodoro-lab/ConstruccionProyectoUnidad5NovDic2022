package com.proyecto.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class DeleteButton {
    JButton button = new JButton();

    public DeleteButton(JTable table) {
        table.getColumn("delete").setCellRenderer((TableCellRenderer) new ButtonRenderer());
        table.getColumn("delete").setCellEditor((TableCellEditor) new ButtonEditor(new JCheckBox()));

        button.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {

                        DefaultTableModel dtm = (DefaultTableModel) table.getModel();

                        if (dtm.getRowCount() == 1) {
                            dtm.setRowCount(0);
                            return;
                        }

                        dtm.removeRow(table.getSelectedRow());
                        info.remove(table.getSelectedRow());
                        JOptionPane.showMessageDialog(null, "Employee deleted successfully");

                    }

                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        // TODO Auto-generated method stub

                    }
                });
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Delete" : value.toString());
            return this;
        }

    }

    class ButtonEditor extends DefaultCellEditor {
        private String label;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            label = (value == null) ? "Delete" : value.toString();
            button.setText(label);

            return button;
        }

        public Object getCellEditorValue() {
            return new String(label);
        }
    }

}
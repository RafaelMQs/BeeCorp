package br.com.beecorp.screens.sideMenu.user.models;

import br.com.beecorp.models.UserModel;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ResultTableModel extends AbstractTableModel {
    private final String DEFAULT_VALUE = "-/-";
    private final String[] COLUMNS = {"id_user", "nm_user", "user_role", "ds_email", "nr_phone", "nr_cep", "ds_address", "dt_creation", "user_active"};
    private List<UserModel> userModels;

    public ResultTableModel(List<UserModel> userModels) {
        this.userModels = userModels;
    }

    @Override
    public int getRowCount() {
        return userModels.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (!userModels.isEmpty()) {
            return switch (columnIndex) {
                case 0 -> userModels.get(rowIndex).getUserId();
                case 1 -> userModels.get(rowIndex).getUserName();
                case 2 -> userModels.get(rowIndex).getUserRole();
                case 3 -> userModels.get(rowIndex).getUserEmail();
                case 4 -> userModels.get(rowIndex).getUserPhone();
                case 5 -> userModels.get(rowIndex).getUserZipCode();
                case 6 -> userModels.get(rowIndex).getUserAddress();
                case 7 -> userModels.get(rowIndex).getUserCreationDate();
                case 8 -> userModels.get(rowIndex).getUserActive();
                default -> DEFAULT_VALUE;
            };
        }
        return DEFAULT_VALUE;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (getValueAt(0, columnIndex) != null) {
            return getValueAt(0, columnIndex).getClass();
        } else {
            return Object.class;
        }
    }
}
package br.com.beecorp.screens.sideMenu.hive.models;

import br.com.beecorp.models.HiveModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ResultTableModelHive extends AbstractTableModel {
    private final String DEFAULT_VALUE = "-/-";
    private final String[] COLUMNS = {"id_hive", "id_user", "cod_hive", "nr_weight", "st_hive", "dt_creation"};
    private List<HiveModel> hiveModels;

    public ResultTableModelHive(List<HiveModel> hiveModels) {
        this.hiveModels = hiveModels;
    }

    @Override
    public int getRowCount() {
        return hiveModels.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (!hiveModels.isEmpty()) {
            return switch (columnIndex) {
                case 0 -> hiveModels.get(rowIndex).getHiveId();
                case 1 -> hiveModels.get(rowIndex).getUserId();
                case 2 -> hiveModels.get(rowIndex).getHiveCode();
                case 3 -> hiveModels.get(rowIndex).getHiveWeight();
                case 4 -> hiveModels.get(rowIndex).getHiveStatus();
                case 5 -> hiveModels.get(rowIndex).getHiveCreationDate();
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

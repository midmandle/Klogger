import javax.swing.table.DefaultTableModel;


public class myTableModel extends DefaultTableModel {

    public myTableModel(String[] columnNames, int i) {
        super(columnNames, i);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

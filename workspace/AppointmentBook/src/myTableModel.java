import javax.swing.table.DefaultTableModel;

/**
 * Basic class to customise the DefaultTableModel to make the calendar grid JTable not editable.
 * @author 14061121
 *
 */
public class myTableModel extends DefaultTableModel {


	private static final long serialVersionUID = 1L;

	public myTableModel(String[] columnNames, int i) {
        super(columnNames, i);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

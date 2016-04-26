package dbconnection;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import net.proteanit.sql.DbUtils;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author Box
 */
public class TableFrame extends JFrame {

    JTextArea txtQuery;
    JScrollPane scrollTxt;
    JScrollPane scrollTable;
    JPanel pnButtons;
    JButton btnCfg;
    JButton btnFetch;
    DBConnectionFrame dbcParent;
    GridBagConstraints c;

    Connection dbConnection;

    public TableFrame(String title) {
        super(title);

        this.setSize(640, 480);

        final int y = (getScreenSize().height) / 2 - this.getHeight() / 2;
        final int x = (getScreenSize().width) / 2 - this.getWidth() / 2;
        //screen center
        this.setLocation(new Point(x, y));

        this.setLayout(new GridBagLayout());

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    if (dbConnection != null) {
                        dbConnection.close();
                    }
                } catch (SQLException mysqlEx) {
                    JOptionPane.showMessageDialog(null, mysqlEx.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initAndSetCompProp();
        addComponentsToPane(this.getContentPane());

        setVisible(true);
    }

    private void initAndSetCompProp() {
        //txtQuery     
        txtQuery = new JTextArea(300, 100);
        txtQuery.setFont(new Font("Bookman Old Style", Font.ROMAN_BASELINE, 15));
        txtQuery.setLineWrap(true);
        scrollTxt = new JScrollPane(txtQuery);

        //pn,btnCfg, btnFetch
        pnButtons = new JPanel();
        pnButtons.setLayout(new GridBagLayout());
        pnButtons.setBorder(BorderFactory.createSoftBevelBorder(1, Color.lightGray, Color.lightGray));
        GridBagConstraints gbc = new GridBagConstraints();
        btnCfg = new JButton("Configuration");
        btnCfg.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (dbcParent != null) {
                    dbcParent.dispose();
                }

                DBConnectionFrame db = new DBConnectionFrame("Connection information");
                db.setVisible(true);
                TableFrame.this.dispose();
            }
        });
        btnFetch = new JButton("Fetch");
        btnFetch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String query = txtQuery.getText();
                try {
                    executeQuery(query);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 50, 0);
        pnButtons.add(btnCfg, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 45;
        gbc.insets = new Insets(0, 0, 0, 0);
        pnButtons.add(btnFetch, gbc);

        //table
        scrollTable = new JScrollPane();
        scrollTable.setBorder(BorderFactory.createSoftBevelBorder(1, Color.lightGray, Color.gray));

    }

    private void addComponentsToPane(Container pane) {
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        //txtQuery      
        c.insets = new Insets(0, 20, 0, 10);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 400;
        c.ipady = 100;
        pane.add(scrollTxt, c);

        //pn  
        c.insets = new Insets(0, 0, 0, 20);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 50;
        c.ipady = 0;
        pane.add(pnButtons, c);

//        //table
        c.insets = new Insets(25, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.ipady = 200;
        c.ipadx = 250;

        pane.add(scrollTable, c);
    }

    public void executeQuery(String sql) throws SQLException {

        // Execute SQL query
        java.sql.Statement stmt = dbConnection.createStatement();
        ResultSet newResultSet = stmt.executeQuery(sql);
        JTable newTable = new JTable(DbUtils.resultSetToTableModel(newResultSet));

        // Display results
        displayNewDataSet(newTable);

    }

    private void displayNewDataSet(JTable table) {

        scrollTable.setViewportView(table);
    }

    private Dimension getScreenSize() {
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        return screenSize;
    }

}

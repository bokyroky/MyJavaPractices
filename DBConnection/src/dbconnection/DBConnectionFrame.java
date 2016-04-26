package dbconnection;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Box
 */
public class DBConnectionFrame extends JFrame {

    JLabel lblUserName;
    JLabel lblPassword;
    JLabel lblUrl;
    JLabel lblDriver;
    JTextField txtUserName;
    JTextField txtPassword;
    JTextField txtUrl;
    JTextField txtDriver;
    JButton btnConnect;
    String _jdbcURL;
    String _dbURL;
    String _username;
    String _password;
    Connection dbConnection;
    TableFrame openTF;

    DBConnectionFrame(String title) {
        super(title);
        this.setSize(800, 320);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addComponentsToPane(this.getContentPane());

    }

    private void addComponentsToPane(Container pane) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 0, 5, 0);

        //postavljanje labela
        lblUserName = new JLabel("User name: ", SwingConstants.RIGHT);
        c.gridx = 1;
        c.gridy = 0;
        pane.add(lblUserName, c);
        lblPassword = new JLabel("Password: ", SwingConstants.RIGHT);
        c.gridx = 1;
        c.gridy = 1;
        pane.add(lblPassword, c);

        lblUrl = new JLabel("Database URL: ", SwingConstants.RIGHT);
        c.gridx = 1;
        c.gridy = 2;
        pane.add(lblUrl, c);

        lblDriver = new JLabel("Driver: ", SwingConstants.RIGHT);
        c.gridx = 1;
        c.gridy = 3;
        pane.add(lblDriver, c);

        //postavljanje tekstualnih polja     
        c.insets = new Insets(0, 0, 5, 20);
        txtUserName = new JTextField();
        c.gridx = 2;
        c.gridy = 0;
        c.ipadx = 100;
        pane.add(txtUserName, c);

        txtPassword = new JTextField();
        c.gridx = 2;
        c.gridy = 1;
        pane.add(txtPassword, c);

        txtUrl = new JTextField("jdbc:sqlserver://localhost:1433;databaseName=AdventureWorksOBP;user=sa;password=SQL;");
        c.gridx = 2;
        c.gridy = 2;
        pane.add(txtUrl, c);

        txtDriver = new JTextField("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        c.gridx = 2;
        c.gridy = 3;
        pane.add(txtDriver, c);

        //postavljanje gumba "Pošalji"
        btnConnect = new JButton("Connect");
        btnConnect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (openTF != null) {
                    openTF.dispose();
                }
                _jdbcURL = getJdbcDrv();

                connectToDBServer();

            }

            private void connectToDBServer() {

                _username = getUserName();
                _password = getPassword();
                _jdbcURL = getJdbcDrv();
                _dbURL = getDbUrl();

                if (registerJDBCDriver()) {

                    if ("".equals(_dbURL)) {
                        JOptionPane.showMessageDialog(null, "Database URL missing!");
                    } else {
                        try {
                            openConnection();
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Warning!", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }
            }

            private boolean registerJDBCDriver() {

                if ("".equals(_jdbcURL)) {
                    JOptionPane.showMessageDialog(null, "Driver information missing!");
                    return false;

                } else {
                    try {

                        Class.forName(_jdbcURL);

                    } catch (ClassNotFoundException e) {

                        JOptionPane.showMessageDialog(null, "JDBC Driver missing!");
                        return false;
                    }
                }
                return true;
            }

            private void openConnection() throws SQLException {

//                if (!"".equals(_username) || !"".equals(_password)) {
//                    // to do
//
//                } 
//                else {
                dbConnection = DriverManager.getConnection(_dbURL);
                if (dbConnection.isValid(5)) {
                    openResultWindow(dbConnection, DBConnectionFrame.this);
                } else {
                    JOptionPane.showConfirmDialog(null, "Database connection URL is not valid!", "Warning!", JOptionPane.ERROR_MESSAGE);
                }

//                }
            }

            private void openResultWindow(Connection dbConnection, DBConnectionFrame dbcParent) {
                openTF = new TableFrame("Table Frame");
                openTF.dbConnection = dbConnection;
                openTF.dbcParent = dbcParent;

            }

        });
        c.gridx = 2;
        c.gridy = 4;
        c.insets = new Insets(0, 350, 0, 20);
        c.anchor = GridBagConstraints.EAST;
        pane.add(btnConnect, c);

        //postavljanje slike
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("info.png"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        JLabel lblPicture = new JLabel(new ImageIcon(img), SwingConstants.LEFT);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 6;
        c.ipadx = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(0, 0, 0, 0);
        pane.add(lblPicture, c);
    }

    private String getJdbcDrv() {

        return txtDriver.getText();

    }

    private String getDbUrl() {
        return txtUrl.getText();

    }

    private String getUserName() {
        return this.txtUserName.toString();
    }

    private String getPassword() {
        return this.txtPassword.toString();
    }

}

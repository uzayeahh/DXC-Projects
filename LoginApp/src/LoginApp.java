import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class LoginApp {
    private JFrame loginFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;
    private JFrame detailsFrame;
    private JFrame restrictedFrame;

    private Connection connection;
    private Statement statement;

    public LoginApp() {
        // replace username and password with database in localhost
        String url = "jdbc:mysql://localhost:3306/users";
        String username = "root";
        String password = "root";

        try {
            // establish connection with database
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            System.out.println("connection established");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // setup login page
        loginFrame = new JFrame("Login Page");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(450, 200);
        loginFrame.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        statusLabel = new JLabel("");

        loginButton.addActionListener(new ActionListener() {
            // event when login button is pressed
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // check if credentials exist in the database
                if (validateLogin(username, password)) {
                    showUserDetails(username, password);
                } else {
                    statusLabel.setText("Invalid username or password");
                }
            }
        });

        loginFrame.add(usernameLabel);
        loginFrame.add(usernameField);
        loginFrame.add(passwordLabel);
        loginFrame.add(passwordField);
        loginFrame.add(loginButton);
        loginFrame.add(statusLabel);

        loginFrame.setVisible(true);
    }

    private boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";

        try {
            // query from the database
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("Query established");
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showUserDetails(String username, String password) {
        loginFrame.setVisible(false);

        // setup login window after logged in
        detailsFrame = new JFrame("User Details");
        detailsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        detailsFrame.setSize(450, 200);
        detailsFrame.setLayout(new GridLayout(3, 1));

        String query = "SELECT * FROM users";

        try {
            // query from database to get necessary information
            ResultSet resultSet = statement.executeQuery(query);
            
            System.out.println("Query established");
            while (resultSet.next()) {
                String checkUsername = resultSet.getString("username");

                if (username.equals(checkUsername)) {
                    String Name = resultSet.getString("name");
                    JLabel nameLabel = new JLabel("Welcome " + Name + "!");
                    detailsFrame.add(nameLabel);

                    int manager = resultSet.getInt("role");
                    if (manager == 1) {
                        JLabel roleLabel = new JLabel("Role: Manager");
                        detailsFrame.add(roleLabel);

                        // add link to restricted page
                        JButton restrictedButton = new JButton("Restricted Page");

                        // event when restricted button is pressed
                        restrictedButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                showRestrictedPage(username, password);
                            }
                        });

                        detailsFrame.add(restrictedButton);
                    } else {
                        JLabel roleLabel = new JLabel("Role: User");
                        detailsFrame.add(roleLabel);
                    }
                } 
            
            }
            
            } catch (SQLException e) {
            e.printStackTrace();
            }

        JLabel usernameLabel = new JLabel("Username: " + username);
        JButton logoutButton = new JButton("Logout");
        
        logoutButton.addActionListener(new ActionListener() {
            // event when logout button is pressed
            public void actionPerformed(ActionEvent e) {
                detailsFrame.setVisible(false);
                loginFrame.setVisible(true);
            }
        });
        
        detailsFrame.add(usernameLabel);
        detailsFrame.add(logoutButton);

        detailsFrame.setVisible(true);
    }

    private void showRestrictedPage(String username, String password){
        detailsFrame.setVisible(false);

        // setup restricted page
        restrictedFrame = new JFrame("Restricted Page");
        restrictedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        restrictedFrame.setSize(450, 200);
        restrictedFrame.setLayout(new GridLayout(1, 2));
        
        JLabel restrictedText = new JLabel("Restricted Page");
        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener() {
            // event to go back to account information page
            public void actionPerformed(ActionEvent e) {
                restrictedFrame.setVisible(false);
                showUserDetails(username, password);
            }
        });
        restrictedFrame.add(restrictedText);
        restrictedFrame.add(backButton);

        restrictedFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginApp();
            }
        });
    }
}

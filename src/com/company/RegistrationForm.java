package com.company;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegistrationForm extends JDialog{
    private JPanel registerPanel;
    private JTextField fname;
    private JTextField email;
    private JTextField phone;
    private JTextField address;
    private JTextField lname;
    private JPasswordField password;
    private JPasswordField confirmpassword;
    private JButton btnRegister;
    private JButton btnCancel;

    public RegistrationForm(JFrame parent) {
        super(parent);
        setTitle("create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(500,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registerUser();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {dispose();}
        });

        setVisible(true);

    }

private void registerUser() throws Exception {
    String fname = this.fname.getText();
    String lname = this.lname.getText();
    String email = this.email.getText();
    String phone = this.phone.getText();
    String address = this.address.getText();
    String password = String.valueOf(this.password.getPassword());
    String Cpassword = String.valueOf(confirmpassword.getPassword());

    if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty() || Cpassword.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "1 or more fields are empty",
                "Please Try Again",
                JOptionPane.ERROR_MESSAGE);
        return;

    }
    if (!password.equals(Cpassword)) {
        JOptionPane.showMessageDialog(this,
                "Your passwords do not match",
                "Please Try Again",
                JOptionPane.ERROR_MESSAGE);
        return;
    }

    user = addUserToDatabase(fname, lname, email, password, phone, address);
    if (user != null) {
        dispose();
    }
    else {
        JOptionPane.showMessageDialog(this,
                "Your registration has failed",
                "Please Try Again",
                JOptionPane.ERROR_MESSAGE );
    }
}
    public User user;
    private User addUserToDatabase(String fname, String lname, String email, String password, String phone, String address) throws Exception {
        User user = null;



           try{
               Connection connection = DriverManager.getConnection("jdbc:ucanaccess\\C:\\Users\\EA210847\\IdeaProjects\\CSPROJECTRESTART\\Databaseyesyes.accdb");

           Statement statement = connection.createStatement();
           ResultSet resultset = statement.executeQuery("Select * from User");
           while (resultset.next())
               System.out.println(resultset.getString(1) + "\t" + resultset.getString(2));

           connection.close();
    }
        catch (Exception e) {
        dispose();}

        try{
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess\\C:\\Users\\EA210847\\IdeaProjects\\CSPROJECTRESTART\\Databaseyesyes.accdb");

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO `user`(`fname`, `lname`, `password`, `email`, `phone`, `address` ) VALUES (?, ?, ?, ?, ?, ?)";


            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(5, phone);
            preparedStatement.setString(6, address);
            preparedStatement.setString(4, password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.fname = fname;
                user.lname = lname;
                user.email = email;
                user.phone = phone;
                user.address = address;
                user.password = password;
            }

            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
    public static void main(String[] args) {
        RegistrationForm myForm = new RegistrationForm(null);
        User user = myForm.user;
        if (user != null) {
            System.out.println("Successful Registration of: " + user.fname + user.lname);

        }
        else {
            System.out.println("registration Canceled");
        }
    }}


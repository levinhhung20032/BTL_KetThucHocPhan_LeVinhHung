package vn.viettuts.qlsv.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import vn.viettuts.qlsv.entity.User;

public class LoginView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPasswordField passwordField;
    private JTextField userNameField;
    private JButton loginBtn;

    public LoginView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel userNameLabel = new JLabel("Username");
        JLabel passwordlabel = new JLabel("Password");
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon("image\\PKA.png"));
        userNameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginBtn = new JButton();

        loginBtn.setText("Login");
        loginBtn.addActionListener(this);

        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        // tạo đối tượng panel để chứa các thành phần của màn hình login
        panel.setSize(400, 300);
        panel.setLayout(layout);
        panel.add(image);
        panel.add(userNameLabel);
        panel.add(passwordlabel);
        panel.add(userNameField);
        panel.add(passwordField);
        panel.add(loginBtn);

        // cài đặt vị trí các thành phần trên màn hình login
        layout.putConstraint(SpringLayout.WEST, userNameLabel, 70, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, userNameLabel, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passwordlabel, 0, SpringLayout.WEST, userNameLabel);
        layout.putConstraint(SpringLayout.NORTH, passwordlabel, 30, SpringLayout.NORTH, userNameLabel);
        layout.putConstraint(SpringLayout.WEST, userNameField, 80, SpringLayout.WEST, userNameLabel);
        layout.putConstraint(SpringLayout.NORTH, userNameField, 0, SpringLayout.NORTH, userNameLabel);
        layout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, userNameField);
        layout.putConstraint(SpringLayout.NORTH, passwordField, 0, SpringLayout.NORTH, passwordlabel);
        layout.putConstraint(SpringLayout.WEST, loginBtn, 0, SpringLayout.WEST, passwordField);
        layout.putConstraint(SpringLayout.NORTH, loginBtn, 30, SpringLayout.NORTH, passwordField);
        layout.putConstraint(SpringLayout.WEST, image, 10, SpringLayout.WEST, userNameLabel);
        layout.putConstraint(SpringLayout.SOUTH, image, -30, SpringLayout.NORTH, userNameField);

        // add panel tới JFrame
        this.add(panel);
        this.pack();
        // cài đặt các thuộc tính cho JFrame
        this.setTitle("Login Page");
        this.setSize(390, 300);
        this.setResizable(false);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public User getUser() {
        return new User(userNameField.getText(), 
                String.copyValueOf(passwordField.getPassword()));
    }

    public void actionPerformed(ActionEvent e) {
    }
    
    public void addLoginListener(ActionListener listener) {
        loginBtn.addActionListener(listener);
    }
}
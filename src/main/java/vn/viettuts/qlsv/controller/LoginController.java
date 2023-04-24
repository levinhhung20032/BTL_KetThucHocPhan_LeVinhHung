package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vn.viettuts.qlsv.dao.UserDao;
import vn.viettuts.qlsv.entity.User;
import vn.viettuts.qlsv.view.LoginView;
import vn.viettuts.qlsv.view.FinancialView;

public class LoginController {
    private final UserDao userDao;
    private final LoginView loginView;

    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
    }
    
    /**
     * Lớp LoginListener 
     * chứa cài đặt cho sự kiện click button "Login"
     */
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                FinancialView financialView = new FinancialView();
                FinancialController financialController = new FinancialController(financialView);
                financialController.showFinancialView();
                loginView.setVisible(false);
            } else {
                loginView.showMessage("Incorrect username or password.");
            }
        }
    }
}

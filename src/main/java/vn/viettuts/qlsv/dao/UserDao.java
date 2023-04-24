package vn.viettuts.qlsv.dao;

import vn.viettuts.qlsv.entity.User;

public class UserDao {
    public boolean checkUser(User user) {
        if (user != null) {
            return "".equals(user.getUserName())
                    && "".equals(user.getPassword());
        }
        return false;
    }
}

package vn.hcmute.service;

import vn.hcmute.dao.IUserDAO_24162113;
import vn.hcmute.dao.UserDAOImpl_24162113;
import vn.hcmute.entity.User_24162113;

public class UserServiceImpl_24162113 implements IUserService_24162113 {
    private final IUserDAO_24162113 userDAO = new UserDAOImpl_24162113();

    @Override
    public String register(User_24162113 user) {
        User_24162113 existing = userDAO.findById(user.getUsername());
        if (existing != null && Boolean.TRUE.equals(existing.getActive())) {
            return "Tên đăng nhập đã tồn tại";
        }
        User_24162113 sameEmail = userDAO.findActiveByEmail(user.getEmail());
        if (sameEmail != null && !sameEmail.getUsername().equals(user.getUsername())) {
            return "Email đã được sử dụng";
        }
        user.setAdmin(false);
        user.setActive(false);
        userDAO.save(user);
        return null;
    }

    @Override
    public User_24162113 login(String username, String password) {
        User_24162113 user = userDAO.findById(username);
        if (user != null && Boolean.TRUE.equals(user.getActive()) && password != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User_24162113 findById(String username) {
        return userDAO.findById(username);
    }

    @Override
    public void activate(String username) {
        User_24162113 user = userDAO.findById(username);
        if (user != null) {
            user.setActive(true);
            userDAO.save(user);
        }
    }
}

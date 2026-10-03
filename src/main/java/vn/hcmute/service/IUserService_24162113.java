package vn.hcmute.service;

import vn.hcmute.entity.User_24162113;

public interface IUserService_24162113 {
    String register(User_24162113 user);

    User_24162113 login(String username, String password);

    User_24162113 findById(String username);

    void activate(String username);
}

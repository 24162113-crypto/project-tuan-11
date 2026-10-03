package vn.hcmute.dao;

import vn.hcmute.entity.User_24162113;

public interface IUserDAO_24162113 {
    User_24162113 findById(String username);

    User_24162113 findActiveByEmail(String email);

    void save(User_24162113 user);
}

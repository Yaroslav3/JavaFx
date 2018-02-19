package service;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> findAll() throws SQLException;

    int save(User user) throws SQLException;

    int update(User user) throws SQLException;

    void delete(int usrId) throws SQLException;
}

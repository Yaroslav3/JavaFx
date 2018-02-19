package service.impl;

import dao.DaoUser;
import dao.impl.DaoUserImpl;
import model.User;
import service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<User> findAll() throws SQLException {
        DaoUser daoUser = new DaoUserImpl();
        return daoUser.findAll();
    }

    @Override
    public int save(User user) throws SQLException {
        DaoUser daoUser = new DaoUserImpl();
        return daoUser.save(user);
    }

    @Override
    public int update(User user) throws SQLException {
        DaoUser daoUser = new DaoUserImpl();
        return daoUser.update(user);
    }

    @Override
    public void delete(int usrId) throws SQLException {
        DaoUser daoUser = new DaoUserImpl();
        daoUser.delete(usrId);
    }
}

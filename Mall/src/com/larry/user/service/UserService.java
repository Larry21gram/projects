package com.larry.user.service;

import com.larry.user.dao.UserDao;
import com.larry.user.domain.User;
import com.larry.user.service.exception.*;
import com.larry.util.EmailUtil;

public class UserService {

    /**
     * 处理用户登录注册相关的逻辑事务
     */

    private UserDao userDao = new UserDao();

    private boolean isEmailFormat(String email){
        if (email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")){
            return true;
        }
        return false;
    }

    public boolean isRegisted(User userForm) throws UserException{
        boolean isEmail = isEmailFormat(userForm.getEmail());
        if (isEmail){
            User userDB = userDao.queryByUsername(userForm.getUsername());
            if (userDB == null){
                int added = userDao.add(userForm);
                if (added > 0){
                    new Thread(new EmailUtil(userForm.getEmail(),userForm.getCode()));
                    return true;
                }
                throw new InsertToDatabaseFailedException();
            }
            throw new UserAlreadyExistedException();
        }
        throw new NotEmailFormatException();
    }


    public User isRight(String username, String password) throws NoUserException, UserNotActiveException {
        User userDB = userDao.queryByUsername(username);
        if (userDB.getPassword().equals(password)){
            if (userDB.getState() == 1){
                return userDB;
            }
            throw new UserNotActiveException();
        }
        throw new NoUserException();
    }

    public int changeState(String code) throws ChangeFailedException {
        int i = userDao.changeByCode(code);
        return i;
    }
}

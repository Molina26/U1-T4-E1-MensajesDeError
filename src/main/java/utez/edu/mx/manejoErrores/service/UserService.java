package utez.edu.mx.manejoErrores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import utez.edu.mx.manejoErrores.entity.UserApp;
import utez.edu.mx.manejoErrores.repository.IUserRepository;

@Service
public class UserService {

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserApp findUserById(long id) {

        try {
            return iUserRepository.getById(id);

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public UserApp findByUsername(String username) {
        return iUserRepository.findUserByUsername(username);
    }

    public List<UserApp> findAllUser() {
        try {
            return iUserRepository.findAll();

        } catch (Exception e) {
            return null;
        }
    }

    public UserApp saveUser(UserApp user) {

        try {
            String passwordEncrypted = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(passwordEncrypted);
            user.normalizeInfo();

            UserApp userobj = iUserRepository.save(user);
            
            return userobj;

        } catch (Exception e) {
            return null;
        }

    }

}

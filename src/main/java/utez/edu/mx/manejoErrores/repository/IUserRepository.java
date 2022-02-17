package utez.edu.mx.manejoErrores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import utez.edu.mx.manejoErrores.entity.UserApp;

import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserApp,Long>{
    public UserApp findUserByUsername(String username);
}

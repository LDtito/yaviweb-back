package com.titulacion.yaviweb.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {
  @Autowired
    private UserRepository userRepository;

    //Crear
    public User save(User entity)
    {
        return userRepository.save(entity);
    }

    //Leer
    public User findById(long id_register)
    {
        return userRepository.findById(id_register).orElse(null);
    }

    //Eliminar
    public void deleteByID(long id_register)
    {
        userRepository.deleteById(id_register);
    }
    
    //Seleccionar Todo
    public List <User> findAll()
    {
        return userRepository.findAll();
    }
  
}

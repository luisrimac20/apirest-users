package com.staxrt.tutorial.service;


import com.staxrt.tutorial.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> Listar();
    Optional<User> ListarId(Long id);
    User creadUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    
    User listaIdUser(Long idUser);
    
    
}

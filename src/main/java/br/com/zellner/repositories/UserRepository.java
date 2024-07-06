package br.com.zellner.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.zellner.models.UserModel;

public interface UserRepository extends CrudRepository<UserModel, String>{

    Optional<UserModel> findByUsername(String username);
    
}

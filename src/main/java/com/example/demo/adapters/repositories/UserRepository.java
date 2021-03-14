package com.example.demo.adapters.repositories;

import com.example.demo.adapters.model.User;
import io.vavr.collection.Seq;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Seq<User> findAll();

    User saveAndFlush(User entity);
}

package br.com.jfernando.mudi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jfernando.mudi.model.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByUsername(String username);

}
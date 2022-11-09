package com.dambroski.webStoreProject.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findByUserEmail(String email);
	
}

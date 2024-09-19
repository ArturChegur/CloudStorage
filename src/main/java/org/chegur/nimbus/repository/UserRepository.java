package org.chegur.nimbus.repository;

import org.chegur.nimbus.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByName(String name);

}

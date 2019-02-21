package org.launchcode.myInventory.models.data;

import org.launchcode.myInventory.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {
    Optional<User> findByName(String username);
}

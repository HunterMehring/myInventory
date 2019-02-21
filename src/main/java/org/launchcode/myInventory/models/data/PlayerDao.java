package org.launchcode.myInventory.models.data;


import org.launchcode.myInventory.models.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PlayerDao extends CrudRepository<Player, Integer> {
    List<Player> findAllByUserId(Long user_id);
}

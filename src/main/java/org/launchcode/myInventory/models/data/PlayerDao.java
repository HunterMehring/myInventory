package org.launchcode.myInventory.models.data;


import org.launchcode.myInventory.models.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PlayerDao extends CrudRepository<Player, Integer> {
}

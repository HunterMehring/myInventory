package org.launchcode.myInventory.models.data;

import org.launchcode.myInventory.models.Changes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ChangeDao extends CrudRepository<Changes, Integer> {
    List<Changes> findAllByUserId(Long user_id);
}

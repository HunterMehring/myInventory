package org.launchcode.myInventory.models.data;

import org.launchcode.myInventory.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {
    List<Category> findAllByUserId(Long user_id); // should i make this an optional?
}

package tn.workbot.coco_marketplace.repositories;

import tn.workbot.coco_marketplace.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserrRepository extends CrudRepository<User,Long> {
}
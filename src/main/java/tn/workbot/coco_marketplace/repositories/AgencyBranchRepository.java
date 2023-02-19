package tn.workbot.coco_marketplace.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.workbot.coco_marketplace.entities.AgencyBranch;

@Repository
public interface AgencyBranchRepository extends CrudRepository<AgencyBranch,Long> {

}
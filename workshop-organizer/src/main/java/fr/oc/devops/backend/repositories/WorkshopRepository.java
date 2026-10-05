package fr.oc.devops.backend.repositories;

import fr.oc.devops.backend.models.WorkshopModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepository extends CrudRepository<WorkshopModel, Integer> {
}

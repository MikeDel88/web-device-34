package fr.oc.devops.backend.repositories;

import fr.oc.devops.backend.models.NotionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface NotionRepository extends CrudRepository<NotionModel, String> {

    List<NotionModel> findByNameIn(Collection<String> names);
}

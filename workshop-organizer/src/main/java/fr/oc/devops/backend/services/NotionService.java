package fr.oc.devops.backend.services;

import fr.oc.devops.backend.models.Notion;
import fr.oc.devops.backend.models.NotionModel;
import fr.oc.devops.backend.repositories.NotionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NotionService {

    @Autowired
    private NotionRepository repository;

    @Autowired
    private ModelMapper mapper;

    public Notion create(Notion notion) {

        NotionModel model = mapper.map(notion, NotionModel.class);

        NotionModel created = repository.save(model);

        return mapper.map(created, Notion.class);
    }

    public List<Notion> getAll() {

        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(w -> mapper.map(w, Notion.class))
                .collect(Collectors.toList());
    }
}

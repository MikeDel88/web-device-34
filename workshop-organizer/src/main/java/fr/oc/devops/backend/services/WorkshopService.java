package fr.oc.devops.backend.services;

import fr.oc.devops.backend.models.Workshop;
import fr.oc.devops.backend.models.WorkshopModel;
import fr.oc.devops.backend.repositories.WorkshopRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private ModelMapper mapper;

    public Workshop create(Workshop workshop) {

        WorkshopModel model = mapper.map(workshop, WorkshopModel.class);
        WorkshopModel created = workshopRepository.save(model);

        return mapper.map(created, Workshop.class);
    }


    public Workshop getById(Integer id) {

        Optional<WorkshopModel> model = workshopRepository.findById(id);

        if (model.isEmpty()) {
            return null;
        }

        return mapper.map(model.get(), Workshop.class);
    }


    public List<Workshop> getAll() {

        return StreamSupport.stream(workshopRepository.findAll().spliterator(), false)
                .map(w -> mapper.map(w, Workshop.class))
                .collect(Collectors.toList());
    }
}

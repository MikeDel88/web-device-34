package fr.oc.devops.backend.controllers;

import fr.oc.devops.backend.WorkshopsApi;
import fr.oc.devops.backend.models.NewWorkshop;
import fr.oc.devops.backend.models.Workshop;
import fr.oc.devops.backend.services.WorkshopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkshopController implements WorkshopsApi {

    @Autowired
    private WorkshopService service;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseEntity<Workshop> createWorkshops(NewWorkshop newWorkshop) {

        Workshop result = service.create(mapper.map(newWorkshop, Workshop.class));

        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Workshop>> listWorkshops(Integer limit) {

        List<Workshop> result = service.getAll();

        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Workshop> showWorkshopById(Integer workshopId) {

        Workshop result = service.getById(workshopId);

        return ResponseEntity.ok(result);
    }
}

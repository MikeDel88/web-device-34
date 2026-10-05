package fr.oc.devops.backend.controllers;

import fr.oc.devops.backend.NotionsApi;
import fr.oc.devops.backend.models.NewNotion;
import fr.oc.devops.backend.models.Notion;
import fr.oc.devops.backend.services.NotionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotionController implements NotionsApi {

    @Autowired
    private NotionService service;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseEntity<Notion> createNotion(NewNotion newNotion) {

        Notion result = service.create(mapper.map(newNotion, Notion.class));

        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Notion>> listNotions() {

        List<Notion> result = service.getAll();

        return ResponseEntity.ok(result);
    }
}

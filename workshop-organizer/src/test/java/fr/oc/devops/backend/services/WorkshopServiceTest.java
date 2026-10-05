package fr.oc.devops.backend.services;

import fr.oc.devops.backend.models.Notion;
import fr.oc.devops.backend.models.NotionModel;
import fr.oc.devops.backend.models.Workshop;
import fr.oc.devops.backend.models.WorkshopModel;
import fr.oc.devops.backend.repositories.NotionRepository;
import fr.oc.devops.backend.repositories.WorkshopRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class WorkshopServiceTest {

    @Mock
    WorkshopRepository workshopRepository;

    @InjectMocks
    WorkshopService workshopService;

    @Spy
    ModelMapper mapper = new ModelMapper();

    WorkshopModel workshopCreated;

    Workshop workshopToCreate;

    @BeforeEach
    void setup() {

        Notion dockerNotion = new Notion();
        dockerNotion.setName("Docker");

        Notion developmentNotion = new Notion();
        developmentNotion.setName("Développement");

        workshopToCreate = new Workshop();
        workshopToCreate.setName("Découvrir le monde du DevOps");
        workshopToCreate.setNotions(List.of(dockerNotion, developmentNotion));

        workshopCreated = mapper.map(workshopToCreate, WorkshopModel.class);
        workshopCreated.setId(1);
    }

    @Test
    void createWorkshop() {

        Mockito.when(workshopRepository.save(Mockito.any(WorkshopModel.class)))
                .thenReturn(workshopCreated);

        Workshop result = workshopService.create(workshopToCreate);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
    }
}

package fr.oc.devops.backend.services;

import fr.oc.devops.backend.models.Notion;
import fr.oc.devops.backend.models.NotionModel;
import fr.oc.devops.backend.repositories.NotionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class NotionServiceTest {

    @Mock
    NotionRepository notionRepository;

    @InjectMocks
    NotionService notionService;

    @Spy
    ModelMapper mapper = new ModelMapper();

    NotionModel notionCreated;

    Notion notionToCreate;

    @BeforeEach
    void setup() {

        notionToCreate = new Notion();
        notionToCreate.setName("Docker");

        notionCreated = mapper.map(notionToCreate, NotionModel.class);
    }

    @Test
    void createNotion() {

        Mockito.when(notionRepository.save(Mockito.any(NotionModel.class)))
                .thenReturn(notionCreated);

        Notion result = notionService.create(notionToCreate);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getName());
        Assertions.assertEquals(notionToCreate.getName(), result.getName());
    }
}

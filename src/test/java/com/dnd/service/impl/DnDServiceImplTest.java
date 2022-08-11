package com.dnd.service.impl;

import com.dnd.dto.DnDDTO;
import com.dnd.exception.CharacterClassNotFoundException;
import com.dnd.model.DnD;
import com.dnd.repository.DnDRepository;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DnDServiceImplTest {

    @Mock
    private DnDRepository dnDRepository;

    @Spy
    private CallExternalAPIServiceImpl callExternalAPIService;

    @InjectMocks
    private DnDServiceImpl dnDService;

    private String dndBaseURL;

    @Before
    public void setUp(){
        dndBaseURL = "http://www.dnd5eapi.co/api";
    }

    @Test
    public void shouldSaveData_ifInputIsCorrect() throws IOException, ParseException {
       final DnDDTO dnDDTO = DnDDTO.builder()
               .name("alex")
               .age(432)
               .classes("bard")
               .races("elf")
               .build();

       final DnD dnD = DnD.builder()
               .name("alex")
               .age(432)
               .classes("bard")
               .races("elf")
               .spells(new JSONArray())
               .equipments(new JSONArray())
               .subclasses(new JSONArray())
               .build();

       given(dnDRepository.save(dnD)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
       Mockito.when(callExternalAPIService.callExternalAPI(dndBaseURL+"/subclasses")).thenReturn(new StringBuilder());

       DnD saveCharacterData = dnDService.saveCharacterData(dnDDTO);
       assertThat(saveCharacterData).isNotNull();

       verify(dnDRepository).save(any(DnD.class));
    }

    @Test
    public void shouldThrowError_ifInputIsInCorrect(){
        final DnDDTO dnDDTO = DnDDTO.builder()
                .name("alex")
                .age(432)
                .races("eweq")
                .build();

        assertThrows(CharacterClassNotFoundException.class,
                () -> dnDService.saveCharacterData(dnDDTO));

        verify(dnDRepository, never()).save(any(DnD.class));
    }
}
package com.dnd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dnd.dto.DnDDTO;
import com.dnd.model.DnD;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = DndController.class)
public class DndControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DndController dndController;

    private String baseUrl;

    @Before
    public void setUp(){
        baseUrl = "/api/character";
    }

    @Test
    public void givenCharacters_whenGetCharacters_thenReturnJsonArray() throws Exception {
        DnD dnD = new DnD("alex");

        List<DnD> dndCharactersList = Collections.singletonList(dnD);

        given(dndController.getCharacterData()).willReturn(new ResponseEntity<>(dndCharactersList, HttpStatus.OK));

        mvc.perform(get(baseUrl+"/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(dnD.getName())));
    }

    @Test
    public void saveCharacterData_whenValidInput_isGiven() throws Exception
    {
        DnDDTO dnD = new DnDDTO("Goku", 232, "fighter", "dwarf");
        mvc.perform(post(baseUrl+"/")
                .content(asJsonString(dnD))
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void throwBadRequestError_whenSavingCharacterData_ifInValidInput_isGiven() throws Exception
    {
        DnDDTO dnD = new DnDDTO("Goku", null, "fighter", "dwarf");
        mvc.perform(post(baseUrl+"/")
                .content(asJsonString(dnD))
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldSuccess_deleteAllCharacters() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(baseUrl+"/"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSuccess_deleteCharacterById() throws Exception {
        String id = "1234";
        mvc.perform(MockMvcRequestBuilders.delete(baseUrl+"/"+id))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
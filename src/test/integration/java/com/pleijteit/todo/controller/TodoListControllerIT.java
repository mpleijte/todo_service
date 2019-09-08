package com.pleijteit.todo.controller;

// class TodoListControllerIT {

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
public class TodoListControllerIT {

    private final String USERNAME = "admin";
    private final String PASSWORD = "admin";
    private final String UUID_STRING = "c415dbea-0b26-46ea-acf3-7f0af87d7a2f";

    final JsonObject POST_DATA_1 = parse("todo 1", "description 1");
    final JsonObject POST_DATA_2 = parse("todo 2", "description 2");
    final JsonObject POST_DATA_3 = parse("todo 3", "description 3");



    @Autowired
    private MockMvc mockMvc;


    // ===
    // Authentication tests
    // ===
    @Test
    public void accessProtected() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void loginUser() throws Exception {
        this.mockMvc.perform(get("/").with(httpBasic("USERNAME", "PASSWORD")))
                .andExpect(authenticated());
    }

    @Test
    public void loginInvalidUser() throws Exception {
        MvcResult result = this.mockMvc.perform(formLogin().user("invalid").password("invalid"))
                .andExpect(unauthenticated())
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }


    // ===
    // CRUD tests
    // ===
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void createList() throws Exception {

        ResultActions resultActions = this.mockMvc.perform(
                post("/lists")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(POST_DATA_1.toString())
                        .with(httpBasic(USERNAME, PASSWORD)))
                        .andExpect(status().isCreated()
                );
        assertTrue("No valid UUID value found for key: id", isUUID(map2JsonObject(resultActions).get("id")));
    }

    @Test
    public void updateList() throws Exception {
        List<ResultActions> todoLists = new ArrayList<>();
        todoLists.add(createTodoList(POST_DATA_1));
        todoLists.add(createTodoList(POST_DATA_2));
        todoLists.add(createTodoList(POST_DATA_3));
    }


    @Test
    public void getTodoLists() throws Exception {
        JsonArray expectedJsonArr = new JsonArray();
        expectedJsonArr.add(parse("rememberTheMilk","shopping list"));
        expectedJsonArr.add(parse("vacation","packing list"));
        expectedJsonArr.add(parse("birthday sister","shopping list for birthday sister"));

        ResultActions resultAction = this.mockMvc
            .perform(get("/lists/")
            .with(httpBasic(USERNAME, PASSWORD)))
            .andExpect(status().isOk());

        JsonArray actualJsonArr = (JsonArray) new JsonParser().parse(getResultAsJson(resultAction));

        JsonArray actualJsonArrWithoutUuid = new JsonArray();
        for(JsonElement actualElement : actualJsonArr) {
            JsonObject actualJsonObject = (JsonObject) actualElement;
            assertTrue("No valid UUID value found for key: id", isUUID(actualJsonObject.get("id")));
            actualJsonObject.remove("id");
            actualJsonArrWithoutUuid.add(actualJsonObject);
        }
        assertThat(actualJsonArrWithoutUuid.toString(), is(expectedJsonArr.toString()));

    }


    @Test
    public void getListById() throws Exception {
        ResultActions result = this.mockMvc
                .perform(get("/lists/" + UUID_STRING)
                .with(httpBasic(USERNAME, PASSWORD)))
                .andExpect(status().isOk())
                .andExpect(content().string("{}"));
    }

    @Test
    public void deleteList() throws Exception {
        // todo
    }

    private JsonObject parse(final String name, final String description) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("description", description);
        return jsonObject;
    }

    private boolean isUUID(JsonElement jsonElement) {
        try {
            UUID.fromString(jsonElement.getAsString());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private String getResultAsJson(ResultActions resultActions) throws UnsupportedEncodingException {
        return resultActions
        .andReturn()
        .getResponse()
        .getContentAsString();
    }

    private JsonObject map2JsonObject(ResultActions resultActions) throws UnsupportedEncodingException {
        return new JsonParser()
                .parse(getResultAsJson(resultActions))
                .getAsJsonObject();
    }

    private ResultActions createTodoList(JsonObject payload) throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                post("/lists")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(payload.toString())
                        .with(httpBasic(USERNAME, PASSWORD))
        ).andExpect(status().isCreated());

        return resultActions;
    }


}

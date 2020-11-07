package org.ashcheulov.models;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class DBService {

    private static int count = 1;

    public static JsonObject getPostById(int id) {
        JsonObject jsonObject = new JsonObject();
        // Обращение к бд и получение кортежа по id
        jsonObject.put("id", id);
        jsonObject.put("title", "TITLE");
        jsonObject.put("body", "Lorem ipsum quisque volutpat bibendum nec placerat luctus diam, eu consequat placerat viverra curae eleifend et suspendisse sociosqu, condimentum ipsum nec proin massa suscipit scelerisque eget nisl mauris odio amet mollis curabitur viverra id leo, adipiscing curabitur porta aptent ad aliquam lobortis nisl, vulputate eget curae dictum justo vulputate iaculis condimentum nibh tellus lectus tempus quisque ullamcorper curabitur hendrerit libero eros donec auctor mollis sapien laoreet augue vulputate egestas lacus sapien, velit aliquet suscipit nostra elementum potenti.");
        jsonObject.put("author","admin");
        jsonObject.put("date","1 nov 2020");
        return jsonObject;
    }

    public static JsonArray getPosts() {
        JsonArray array = new JsonArray();
        array.add(getPostById(1));
        array.add(getPostById(3));
        array.add(getPostById(2));
//        System.out.println(array.toString());
        return array;
    }
}

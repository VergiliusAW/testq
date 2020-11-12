package org.ashcheulov.models;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class DBService {

    /**
     * Получение полного поста по id
     * @param id
     * @return
     */
    public static JsonObject getPostById(int id) {
        JsonObject jsonObject = new JsonObject();
        // Обращение к бд и получение кортежа по id
        jsonObject.put("id", id);
        jsonObject.put("title", "TITLE");
         //Полное тело статьи
        jsonObject.put("body", "Lorem ipsum quisque volutpat bibendum nec placerat luctus diam, eu consequat placerat viverra curae eleifend et suspendisse sociosqu, condimentum ipsum nec proin massa suscipit scelerisque eget nisl mauris odio amet mollis curabitur viverra id leo, adipiscing curabitur porta aptent ad aliquam lobortis nisl, vulputate eget curae dictum justo vulputate iaculis condimentum nibh tellus lectus tempus quisque ullamcorper curabitur hendrerit libero eros donec auctor mollis sapien laoreet augue vulputate egestas lacus sapien, velit aliquet suscipit nostra elementum potenti.");
        jsonObject.put("author","admin");
        jsonObject.put("date","1 nov 2020");
        return jsonObject;
    }

    /**
     * Получить первью поста по id
     * @param id
     * @return
     */
    private static JsonObject getPreview(int id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("id", id);
        jsonObject.put("title", id);
        //Первьюха для отображения на главной TODO: ограничить размер
        jsonObject.put("preview", "Lorem ipsum quisque volutpat bibendum nec placerat luctus diam, eu consequat placerat viverra curae eleifend et suspendisse sociosqu, condimentum ipsum nec proin massa suscipit scelerisque eget nisl mauris odio amet mollis curabitur viverra id leo, adipiscing curabitur porta aptent ad aliquam lobortis nisl, vulputate eget curae dictum justo vulputate iaculis condimentum nibh tellus lectus tempus quisque ullamcorper curabitur hendrerit libero eros donec auctor mollis sapien laoreet augue vulputate egestas lacus sapien, velit aliquet suscipit nostra elementum potenti.");
        return jsonObject;
    }

    /**
     * Получение всех постов
     * @return
     */
    public static JsonArray getPosts() {
        JsonArray array = new JsonArray();
        for (int i = 0; i < 10; i++) {
            array.add(getPreview(i));
        }
        return array;
    }

    public static boolean login() {
        return true;
    }

    public static boolean register() {
        return true;
    }

    public static boolean recover() {
        return true;
    }
}

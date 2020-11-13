package org.ashcheulov.models;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.ashcheulov.models.tables.Posts;
import org.ashcheulov.models.tables.Users;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class DBService {

    @Inject
    EntityManager entityManager;

    /**
     * Получение полного поста по id
     * @param id
     * @return
     */
    public JsonObject getPostById(int id) {
//        JsonObject jsonObject = new JsonObject();
//        // Обращение к бд и получение кортежа по id
//        jsonObject.put("id", id);
//        jsonObject.put("title", "TITLE");
//         //Полное тело статьи
//        jsonObject.put("body", "Lorem ipsum quisque volutpat bibendum nec placerat luctus diam, eu consequat placerat viverra curae eleifend et suspendisse sociosqu, condimentum ipsum nec proin massa suscipit scelerisque eget nisl mauris odio amet mollis curabitur viverra id leo, adipiscing curabitur porta aptent ad aliquam lobortis nisl, vulputate eget curae dictum justo vulputate iaculis condimentum nibh tellus lectus tempus quisque ullamcorper curabitur hendrerit libero eros donec auctor mollis sapien laoreet augue vulputate egestas lacus sapien, velit aliquet suscipit nostra elementum potenti.");
//        jsonObject.put("author","admin");
//        jsonObject.put("date","1 nov 2020");
        return postToJSON(entityManager.find(Posts.class,1));
    }

    /**
     * Получить первью поста по id
     * @param id
     * @return
     */
    private JsonObject getPreview(int id) {
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
    public JsonArray getPosts() {

        JsonArray array = new JsonArray();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Posts> cq = cb.createQuery(Posts.class);
        Root<Posts> rootEntry = cq.from(Posts.class);
        CriteriaQuery<Posts> all = cq.select(rootEntry);

        TypedQuery<Posts> allQuery = entityManager.createQuery(all);

        allQuery.getResultList().forEach(post -> array.add(postToPreviewJSON(post)));

        return array;
    }

    public boolean login() {
        Users user = new Users();
        user.setEmail("a@a.a");
        user.setPassword("123");
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
//        map.put("password",user.getPassword());

        System.out.println(entityManager.find(Users.class,map).getRole());
//        if (entityManager)
//            return true;
//        else
            return false;
    }

    public boolean register() {
        return true;
    }

    public boolean recover() {
        return true;
    }

    private JsonObject postToJSON(Posts post) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("id",post.getId());
        jsonObject.put("title",post.getTitle());
        jsonObject.put("body",post.getBody());
        return jsonObject;
    }

    private JsonObject postToPreviewJSON(Posts post) {

        String preview = post.getBody();

        if (preview.length()>400)
            preview = preview.substring(0,400) + "...";

        JsonObject jsonObject = new JsonObject();
        jsonObject.put("id",post.getId());
        jsonObject.put("title",post.getTitle());
        jsonObject.put("preview",preview);
        return jsonObject;
    }
}

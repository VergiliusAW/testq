package org.ashcheulov.models;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.ashcheulov.models.tables.Posts;
import org.ashcheulov.models.tables.Sessions;
import org.ashcheulov.models.tables.Users;
import org.hibernate.Session;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.Cookie;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class DBService {

    @Inject
    EntityManager entityManager;

    /**
     * Получение полного поста по id
     *
     * @param id
     * @return
     */
    public JsonObject getPostById(int id) {
        return postToJSON(entityManager.find(Posts.class, id));
    }

    /**
     * Получить первью поста по id
     *
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
     *
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

    /**
     * Логин. На сервер поступает запрос на логин. Сервер обращается к бд. Ищет пользователя с указанным логином
     * и паролем. Если находит, то возвращает id
     *
     * @param req
     * @return
     */
    public int login(JsonObject req) {
        Users user = new Users();
        user.setEmail(req.getString("email"));
        user.setPassword(req.getString("password"));

        List<Users> usersList = entityManager.createQuery("SELECT u FROM Users u WHERE u.email=:e and u.password=:p",
                Users.class)
                .setParameter("e", user.getEmail())
                .setParameter("p", user.getPassword()).getResultList();

        if (usersList.size() == 1)
            return usersList.get(0).getId();
        else
            return -1;
    }

    /**
     * Регистрация. На сервер поступает запрос на регистрацию. Сервер проверяет, нет ли уж зарегистрированного
     * пользователя с таким логином и паролем. Если нет, то добавляет в бд.
     *
     * @param req
     * @return
     */
    public int register(JsonObject req) {
        Users client = new Users();
        client.setEmail(req.getString("email"));
        client.setPassword(req.getString("password"));
        client.setRole("client");

        List<Users> usersList = entityManager.createQuery("SELECT u FROM Users u WHERE u.email=:e",
                Users.class)
                .setParameter("e", client.getEmail()).getResultList();

        if (usersList.size() == 0) {
            Session session = entityManager.unwrap(Session.class);
            session.save(client);

            usersList = entityManager.createQuery("SELECT u FROM Users u WHERE u.email=:e and u.password=:p",
                    Users.class)
                    .setParameter("e", client.getEmail())
                    .setParameter("p", client.getPassword()).getResultList();

            System.out.println(usersList.size());

            return usersList.get(0).getId();
        } else
            return -1;
    }

    /**
     * Восстановление. На сервер поступает запрос на восстановление пароля. Сервер обращается к бд и проверяет есть ли
     * пользователь с таким логином. Если есть, то высылает письмо с ссылкой на смену логина.
     *
     * @param req
     * @return
     */
    public boolean recover(JsonObject req) {
        Users user = new Users();
        user.setEmail(req.getString("email"));

        List<Users> usersList = entityManager.createQuery("SELECT u FROM Users u WHERE u.email=:e",
                Users.class)
                .setParameter("e", user.getEmail()).getResultList();
        if (usersList.size() != 0)
            return true;
        else
            return false;
    }

    public void addSession(UUID uuid, int user_id) {
        Sessions sessions = new Sessions();
        sessions.setUser_id(user_id);
        sessions.setUuid(uuid);

        Session session = entityManager.unwrap(Session.class);
        session.save(sessions);
    }

    public int checkSession(Cookie cookie) {
        List<Sessions> sessionsList = entityManager.createQuery("SELECT s FROM Sessions s WHERE s.uuid=:u",
                Sessions.class).setParameter("u", UUID.fromString(cookie.getValue())).getResultList();
        if (sessionsList.size() == 0)
            return -1;
        else
            return sessionsList.get(0).getUsers().getId();
    }

    /**
     * Переводит полученную запись из таблицы posts в формат JSON
     *
     * @param post
     * @return
     */
    private JsonObject postToJSON(Posts post) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("id", post.getId());
        jsonObject.put("title", post.getTitle());
        jsonObject.put("body", post.getBody());
        return jsonObject;
    }

    /**
     * Переводит полученную запись из таблицы posts в формат превьюхи JSON с ограничением на количество символов
     *
     * @param post
     * @return
     */
    private JsonObject postToPreviewJSON(Posts post) {

        String preview = post.getBody();
        if (preview.length() > 400)
            preview = preview.substring(0, 400) + "...";
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("id", post.getId());
        jsonObject.put("title", post.getTitle());
        jsonObject.put("preview", preview);
        return jsonObject;
    }

    public Users getUserById(int id) {
        return entityManager.find(Users.class, id);
    }

    public boolean addPost(int id, JsonObject s) {
        Posts post = new Posts();
        post.setAuthor_id(id);
        post.setTitle(s.getString("title"));
        post.setBody(s.getString("body").replace("\n","<br>"));

        Session session = entityManager.unwrap(Session.class);
        session.save(post);
        return true;
    }
}

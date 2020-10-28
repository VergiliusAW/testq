package org.ashcheulov.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class TemplateDB {

    private static int USERS_COUNT;
    private final List<User> users;

    {
        users = new ArrayList<>();
        users.add(new User(++USERS_COUNT,"Tom","tom@local.ru","123"));
        users.add(new User(++USERS_COUNT,"Mikhail","mikhail@local.ru","password"));
        users.add(new User(++USERS_COUNT,"Sergey", "sergey@local.ru", "321"));
        users.add(new User(++USERS_COUNT,"Olga", "olga@local.ru","qwerty"));
    }

    public List<User> index() {
        return users;
    }

    public User show(int id) {
        return users.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public int getId(String email) {
        return users.stream().filter(person -> person.getEmail().equals(email)).findAny().get().getId();
    }

    public boolean login(String email, String password) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }

    public boolean register(String email, String password) {
        return users.add(new User(++USERS_COUNT," ",email,password));
    }
}

package org.ashcheulov.utils;

import org.ashcheulov.models.DBService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.NewCookie;
import java.util.UUID;

@ApplicationScoped
public class Helper {

    @Inject
    DBService dbService;

    /**
     * Забабахать новую куку для конкретного пользователя
     * @param id
     * @return
     */
    public NewCookie getNewSessionCookie(int id) {

        UUID uuid = UUID.randomUUID();
        NewCookie cookie = new NewCookie("session", uuid.toString(), "/", "localhost", 1,
                "comment", 60, false);
        dbService.addSession(uuid, id);

        return cookie;
    }
}

package com.application.spring.dao.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.application.spring.model.UserSession;
import com.application.spring.repositories.UserSessionRepository;

@Repository
public class SessionDAOImpl implements SessionDAO {

    @Autowired
    private UserSessionRepository userSessionRepository;

    /**
    * Creates a new UserSession.
    *
    * @return The newly created UserSession.
    */
    @Override
    public UserSession createSession() {
        return userSessionRepository.save(new UserSession());
    }

    /**
    * Retrieves a UserSession object based on the provided token.
    *
    * @param token the session token
    * @return the UserSession object associated with the token, or null if not found
    */
    @Override
    public UserSession getSessionByToken(String token) {
        return userSessionRepository.findBySessionToken(token).orElse(null);
    }

}

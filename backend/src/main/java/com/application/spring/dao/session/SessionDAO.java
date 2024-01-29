package com.application.spring.dao.session;

import com.application.spring.model.UserSession;

public interface SessionDAO {

    UserSession createSession();

    UserSession getSessionByToken(String token);
    
} 

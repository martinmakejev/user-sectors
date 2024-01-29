package com.application.spring.dao.user;

import java.util.List;
import com.application.spring.model.*;

/**
 * This interface represents a Data Access Object (DAO) for managing User entities.
 * It provides methods for retrieving, creating, updating, and saving User objects.
 */
public interface UserDAO {
    List<User> getAllUsers();

    User createUser(String name, Boolean terms, UserSession session, List<Sector> sectorIds);

    User getUserById(Long id);

    User saveUser(User user);

    User getUserByToken(UserSession sessionToken);

    User updateUser(String name, Boolean terms, List<Sector> sectors, UserSession session);
}

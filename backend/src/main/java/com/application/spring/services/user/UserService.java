package com.application.spring.services.user;

import com.application.spring.dto.UserDTO;
import java.util.List;
import java.util.Optional;

/**
 * This interface represents a service for managing users.
 */
public interface UserService {
        /**
         * Retrieves a list of all users.
         *
         * @return a list of UserDTO objects representing the users
         */
        List<UserDTO> getAllUsers();

        /**
         * Retrieves a user based on the provided session ID.
         *
         * @param sessionId the session ID of the user
         * @return a UserDTO object representing the user
         */
        Optional<UserDTO> getUserBySessionId(String sessionId);

        /**
         * Creates a new user.
         *
         * @param requestBody The request body containing the new user data.
         * @return a UserDTO object representing the user
         */
        UserDTO createUser(UserDTO requestBody);

        /**
         * Updates the user data based on the session ID.
         *
         * @param requestBody The request body containing the updated user data.
         * @param sessionId   The session ID of the user.
         * @return a UserDTO object representing the updated user data.
         */
        UserDTO updateUser(UserDTO requestBody, String sessionId);
}
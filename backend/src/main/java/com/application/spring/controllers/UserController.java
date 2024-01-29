package com.application.spring.controllers;

import com.application.spring.dto.UserDTO;
import com.application.spring.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Retrieves a list of all users.
     *
     * @return a list of User objects representing all users
     */
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Retrieves a User object based on the provided session ID.
     *
     * @param id the session ID of the user
     * @return the User object associated with the session ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserBySessionId(@PathVariable String id) {
        try {
            Optional<UserDTO> userDTO = userService.getUserBySessionId(id);

            if (userDTO.isPresent()) {
                return ResponseEntity.ok(userDTO.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    /**
     * Creates a new user based on the provided request body.
     *
     * @param requestBody the request body containing the user data
     * @return the created user
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO requestBody) {
        try {
            UserDTO createdUser = userService.createUser(requestBody);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    /**
     * Updates a user with the given request body and ID.
     *
     * @param requestBody the request body containing the updated user information
     * @param id          the ID of the user to be updated
     * @return the updated User object
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO requestBody, @PathVariable String id) {
        try {
            UserDTO updatedUser = userService.updateUser(requestBody, id);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
package com.application.spring.dao.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.application.spring.dao.sector.SectorDAO;
import com.application.spring.model.Sector;
import com.application.spring.model.User;
import com.application.spring.model.UserSession;
import com.application.spring.repositories.UserRepository;
import java.util.stream.Collectors;

/**
 * Implementation of the UserDAO interface that provides CRUD operations for
 * User entities.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SectorDAO sectorDao;

    /**
     * Retrieves all users from the database.
     *
     * @return a list of all users
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Creates a new user with the specified name, terms, session, and sectors.
     *
     * @param name      the name of the user
     * @param terms     the terms agreed by the user
     * @param session   the user session
     * @param sectorIds the list of sectors associated with the user
     * @return the created user
     */
    @Override
    public User createUser(String name, Boolean terms, UserSession session, List<Sector> sectorIds) {
        User user = new User( name, terms, updateUserSectors(sectorIds), session);
        return userRepository.save(user);
    }

    /**
     * Updates an existing user with the specified name, terms, sectors, and
     * session.
     *
     * @param name    the new name of the user
     * @param terms   the new terms agreed by the user
     * @param sectors the new list of sectors associated with the user
     * @param session the user session
     * @return the updated user
     */
    @Override
    public User updateUser(String name, Boolean terms, List<Sector> sectorIds, UserSession session) {
        User existingUser = userRepository.findBySession(session);
        existingUser.setName(name);
        existingUser.setTerms(terms);
        existingUser.setSectors(updateUserSectors(sectorIds));
        return userRepository.save(existingUser);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user
     * @return the user with the specified ID, or null if not found
     */
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    /**
     * Saves a user to the database.
     *
     * @param user the user to be saved
     * @return the saved user
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their session token.
     *
     * @param sessionToken the session token of the user
     * @return the user with the specified session token, or null if not found
     */
    @Override
    public User getUserByToken(UserSession sessionToken) {
        return userRepository.findBySession(sessionToken);
    }

    // Helper method to update user details

    /**
     * Updates the sectors of a user.
     *
     * @param sectorIds the list of sector IDs to update
     * @return the updated list of sectors
     */
    private List<Sector> updateUserSectors(List<Sector> sectorIds) {
        return sectorIds.stream()
                .map(sector -> sectorDao.getSector(sector.getId()))
                .collect(Collectors.toList());
    }
}

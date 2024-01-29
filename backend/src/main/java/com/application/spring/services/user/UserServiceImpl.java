package com.application.spring.services.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.spring.dao.session.SessionDAO;
import com.application.spring.dao.user.UserDAO;
import com.application.spring.dto.SectorDTO;
import com.application.spring.dto.UserDTO;
import com.application.spring.dto.UserSessionDTO;
import com.application.spring.model.*;

/**
 * Implementation of the UserService interface.
 * Provides methods for retrieving user data.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private SessionDAO sessionDao;

    /**
     * Retrieves a list of all users along with their associated sectors.
     *
     * @return A list of UserDTO objects representing the users and their sectors.
     */
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userDAO.getAllUsers();
        return users.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a UserDTO based on the provided session ID.
     *
     * @param sessionId The session ID associated with the user.
     * @return A UserDTO representing the user's information.
     *         Returns null if no user is found for the given session ID.
     */
    @Override
    public Optional<UserDTO> getUserBySessionId(String sessionId) {
        UserSession sessionToken = sessionDao.getSessionByToken(sessionId);

        if (sessionToken != null) {
            User user = userDAO.getUserByToken(sessionToken);
            return Optional.of(mapToDTO(user));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Creates a new user.
     *
     * @param requestBody The request body containing the user data.
     * @return A UserDTO object representing the user.
     */
    @Override
    public UserDTO createUser(UserDTO requestBody) {
        String name = requestBody.getName();
        Boolean terms = requestBody.getTerms();
        List<SectorDTO> sectorIds = requestBody.getSectors();
        List<Sector> sectors = convertSectors(sectorIds);

        validateUserInput(name, terms, sectorIds);

        UserSession session = sessionDao.createSession();

        return mapToDTO(userDAO.createUser(name, terms, session, sectors));
    }

    /**
     * Updates the user data based on the session ID.
     *
     * @param requestBody The request body containing the updated user data.
     * @param sessionId   The session ID of the user.
     * @return a UserDTO object representing the updated user data.
     */
    @Override
    public UserDTO updateUser(UserDTO requestBody, String sessionId) {
        UserSession sessionToken = sessionDao.getSessionByToken(sessionId);

        if (sessionToken != null) {
            String name = requestBody.getName();
            Boolean terms = requestBody.getTerms();
            List<SectorDTO> sectorIds = requestBody.getSectors();
            List<Sector> sectors = convertSectors(sectorIds);

            validateUserInput(name, terms, sectorIds);

            return mapToDTO(userDAO.updateUser(name, terms, sectors, sessionToken));
        } else {
            throw new IllegalArgumentException("Invalid session ID");
        }
    }

    // Helper functions bellow

    private List<Sector> convertSectors(List<SectorDTO> sectorIds) {
        return sectorIds.stream()
                .map(sector -> new Sector(sector.getId(), sector.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Validates the user input for name, terms, and sectors.
     * 
     * @param name  the name of the user
     * @param terms the terms agreed by the user
     * @param list  the list of sector IDs selected by the user
     * @throws IllegalArgumentException if any of the input parameters are null or
     *                                  if the number of selected sectors is invalid
     */
    private void validateUserInput(String name, Boolean terms, List<SectorDTO> list) {
        if (name == null || terms == null || list == null) {
            throw new IllegalArgumentException("You must provide a name, terms, and sectors");
        }

        if (!name.matches("^[a-zA-ZäöõüÄÖÕÜ ]+$")) {
            throw new IllegalArgumentException("Invalid name format. Name must contain only letters and spaces.");
        }

        int sectorIdsCount = list.size();
        if (sectorIdsCount < 1) {
            throw new IllegalArgumentException("You must select at least 1 sector");
        } else if (sectorIdsCount > 5) {
            throw new IllegalArgumentException("You can only select up to 5 sectors");
        }
    }

    /**
     * Maps a User object to a UserDTO object.
     *
     * @param user the User object to map
     * @return the mapped UserDTO object
     */
    public UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getName(),
                user.getTerms(),
                user.getSectors().stream().map(sector -> new SectorDTO(sector.getId(), sector.getName()))
                        .collect(Collectors.toList()),
                new UserSessionDTO(user.getSession().getSessionToken()));
    }
}

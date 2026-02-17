package fr.pompey.cda24060.webappdemo.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import fr.pompey.cda24060.webappdemo.config.CustomProperty;
import fr.pompey.cda24060.webappdemo.model.Users;

import java.util.List;

@Repository
@Slf4j
public class UsersRepository {

    @Autowired
    private CustomProperty property;

    public Iterable<Users> getAllUsers() {
        // recupération de ma proprietes contenant l'url de l'API
        String baseURL = property.getApiURL();
        // construction de l'url pour appel à l'API
        String getAllUsersURL = baseURL + "/all-users";
        // construction de la requete HTTP
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Users>> response = restTemplate.exchange(
                getAllUsersURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        log.debug("Get Users call {}", response.getStatusCode());
        // envoi de la reponse.
        return response.getBody();
    }

    public Users getUsers(int id_users) {
        String baseApiUrl = property.getApiURL();
        String getUsersUrl = baseApiUrl + "/users/" + id_users;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Users> response = restTemplate.exchange(
                getUsersUrl,
                HttpMethod.GET,
                null,
                Users.class
        );

        log.debug("Get User call " + response.getStatusCode());

        return response.getBody();
    }

    public Users createUsers(Users users) {
        String baseApiUrl = property.getApiURL();
        String createUsersUrl = baseApiUrl + "/users";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Users> request = new HttpEntity<>(users);
        ResponseEntity<Users> response = restTemplate.exchange(
                createUsersUrl,
                HttpMethod.POST,
                request,
                Users.class);

        log.debug("Create User call " + response.getStatusCode());

        return response.getBody();
    }

    public Users updateUsers(Users users) {
        String baseApiUrl = property.getApiURL();
        String updateUsersUrl = baseApiUrl + "/users/" + users.getId_user();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Users> request = new HttpEntity<>(users);
        ResponseEntity<Users> response = restTemplate.exchange(
                updateUsersUrl,
                HttpMethod.PUT,
                request,
                Users.class);

        log.debug("Update User call " + response.getStatusCode());

        return response.getBody();
    }

    public void deleteUsers(int id) {
        String baseApiUrl = property.getApiURL();
        String deleteUsersUrl = baseApiUrl + "/users/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteUsersUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete User call " + response.getStatusCode());
    }

}

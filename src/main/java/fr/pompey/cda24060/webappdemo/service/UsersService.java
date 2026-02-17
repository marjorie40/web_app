package fr.pompey.cda24060.webappdemo.service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.pompey.cda24060.webappdemo.model.Users;
import fr.pompey.cda24060.webappdemo.repository.UsersRepository;

@Data
@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Users getUsers(Integer id) {
        // appel au repository
        return usersRepository.getUsers(id);
    }

    public Iterable<Users> getAllUsers() {
        return usersRepository.getAllUsers();
    }

    public void deteleUsers(final Integer id) {
        usersRepository.deleteUsers(id);
    }

    public Users saveUsers(Users users) {
        Users saved;
        //System.out.println(users.toString());
        // Règle de gestion : Le nom de famille doit être mis en majuscule.
        users.setLastname(users.getLastname().toUpperCase());

        if(users.getId_user() == null) {
            // Si l'id est null, alors c'est un nouveau user.
            saved = usersRepository.createUsers(users);
        } else {
            // sinon c'est une mise à jour.
            saved = usersRepository.updateUsers(users);
        }
        return saved;
    }
}

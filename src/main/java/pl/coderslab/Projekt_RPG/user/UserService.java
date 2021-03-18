package pl.coderslab.Projekt_RPG.user;


public interface UserService {
    User findByUserName(String name);
    void saveUser(User user);
}

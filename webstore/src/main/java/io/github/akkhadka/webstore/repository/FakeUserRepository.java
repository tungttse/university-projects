package io.github.akkhadka.webstore.repository;
import io.github.akkhadka.webstore.model.Role;
import io.github.akkhadka.webstore.model.User;
import io.github.akkhadka.webstore.model.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FakeUserRepository implements UserRepository {
    private static HashMap<String, User> users;

    private static HashMap<String, User> getUsers(){
        if(users == null){
            synchronized (FakeUserRepository.class){
                if(users==null){
                    users = new HashMap<>();
                    users.put("arjun",new User("arjun","a123456","Arjun","Khadka","arjun@gmail.com"));
                    users.put("rakesh",new User("rakesh","a123456","Rakesh","shrestha","rakesh@gmail.com"));
                    users.put("andycole", new User("andycole","a123456","Andy","Cole","andycole@gmail.com"));

                    //hard code admin
                    User adminA = new User("a","a","Admin A","Andy","aa@gmail.com");
                    adminA.setRole(Role.ADMIN);
                    users.put("a", adminA);

                    User adminB = new User("b","b","Admin B","Cole","b@gmail.com");
                    adminB.setRole(Role.ADMIN);
                    users.put("b", adminB);

                    users.put("c", new User("c","c","c","cc","ccc@gmail.com"));



                }
            }
        }
        return users;
    }
    @Override
    public void update(User entity) {

        getUsers().put(entity.getUsername(),entity);
    }

    @Override
    public void save(User entity) {

        getUsers().put(entity.getUsername(),entity);
    }

    @Override
    public void remove(User entity) {

    }

    @Override
    public User find(String s) {
        return getUsers().get(s);
    }

    @Override
    public List<User> findAll() {
       List<User> list = new ArrayList(getUsers().values());
       return list;
    }

    public User findByUsername(String userName) {
        List<User> users = findAll();
        return   users.stream().filter(x->x.getUsername().equals(userName)).findAny().orElse(null);
    }

}

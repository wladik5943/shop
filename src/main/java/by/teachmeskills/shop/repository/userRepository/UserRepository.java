package by.teachmeskills.shop.repository.userRepository;

import by.teachmeskills.shop.entity.User;

import java.util.Collection;

public  interface UserRepository {
    public void add(User user);
    public void deleteById(int userId);
    public User loginSearch(String login);
    public Collection<User> all();
    public void updateUser(User user);
    public User idSearch(int id);

}

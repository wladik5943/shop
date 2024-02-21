package by.teachmeskills.shop;

import by.teachmeskills.shop.entity.User;
import by.teachmeskills.shop.repository.FileRepository;

import java.util.Collection;

public class main {
    public static void main(String[] args) {
        FileRepository fileRepository = new FileRepository();

        User user = new User(1,"serega","wlad","1233","33221");
        User user1 = new User(2,"leonid","4332","6785","rop");
        User user2 = new User(3,"vlad","wlad","1233","33221");
        User user3 = new User(4,"dan","4332","6785","rop");

        fileRepository.add(user);
        fileRepository.add(user1);
        fileRepository.add(user2);
        fileRepository.add(user3);

        Collection<User> users = fileRepository.allUsers();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.stream().toList().get(i).getName());
        }
        System.out.println("<<<<<<<<<<<");

        fileRepository.deleteByld(3);

        users = fileRepository.allUsers();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.stream().toList().get(i).getName());
        }
    }
}

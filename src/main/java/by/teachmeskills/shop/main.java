package by.teachmeskills.shop;

import by.teachmeskills.shop.entity.User;
import by.teachmeskills.shop.repository.FileRepository;

public class main {
    public static void main(String[] args) {
        FileRepository fileRepository = new FileRepository();

        User user = new User(1,"vlad","wlad","1233","33221");
        User user1 = new User(2,"dan","4332","6785","rop");


        fileRepository.add(user);
        fileRepository.add(user1);

        System.out.println(fileRepository.allUsers().toString());
    }
}

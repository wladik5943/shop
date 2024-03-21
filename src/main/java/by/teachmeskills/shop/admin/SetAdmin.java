package by.teachmeskills.shop.admin;

import by.teachmeskills.shop.entity.User;
import by.teachmeskills.shop.enums.UserRole;
import by.teachmeskills.shop.repository.UserRepository;

import java.util.Scanner;

class SetAdmin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Присваивание ADMIN статуса пользователю");
        System.out.println("введите id пользователя");
        int id = scanner.nextInt();
        UserRepository userRepository = new UserRepository();
        User user = userRepository.idSearch(id);
        if(user == null){
            System.out.println("пользователь не существует");
            System.exit(0);
        } else if (user.getRole() == UserRole.ADMIN) {
            System.out.println("пользователь уже является админом");
            System.exit(0);
        }
        boolean flag = true;
        while (flag) {
            System.out.println("выбранный пользователь");
            System.out.println(user.toString());
            System.out.println("присвоить статус админа? (y/n)");
            String ver =  scanner.next();
            char verify = ver.charAt(0);
            switch (verify) {
                case 'n', 'N': {
                    System.exit(0);
                }
                case 'Y', 'y':{
                    flag = false;
                }
            }
            if(flag){
                System.out.println("некорректное подтверждение");
                System.out.println("повторите попытку");
            }
        }


        user.setRole(UserRole.ADMIN);
        userRepository.updateUser(user);
        System.out.println("статус админа присвоен");
    }


}

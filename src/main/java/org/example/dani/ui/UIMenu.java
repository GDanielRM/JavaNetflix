package org.example.dani.ui;

import org.example.dani.entity.User;
import org.example.dani.enums.Enums.UserType;
import org.example.dani.util.UtilEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class UIMenu {

    public static EntityManager em = null;
    public static User userLogged;

    public static void showMenu(){
        System.out.println("-*-*-* Welcome *-*-*-");
        System.out.println();

        int response;
        do {
            System.out.println("Select an option: ");
            System.out.println("1. Loggin");
            System.out.println("0. Exit");

            Scanner sc = new Scanner(System.in);
            response = Integer.parseInt(sc.nextLine());

            switch (response){
                case 1:
                    response = 0;
                    authUser();
                    break;
                case 0:
                    System.out.println("Thanks for your visit.");
                    System.out.println("Good bye :)");
                    break;
                default:
                    System.out.println("Please select a correct answer");
                    break;
            }
        } while (response != 0);
    }

    private static void authUser() {
        em = UtilEntity.getEntityManager();

        try {
            System.out.println("Insert your email:");
            Scanner sc = new Scanner(System.in);
            String email = sc.nextLine();

            System.out.println("Insert your password:");
            String password = sc.nextLine();

            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password",User.class);
            query.setParameter("email",email);
            query.setParameter("password",password);
            List<User> users = query.getResultList();

            if (users.isEmpty()){
                throw new Exception("Incorret credentials");
            }

            userLogged = users.get(0);

            if (userLogged.getUserType().equals(UserType.USER.getCode())) {
                UIUser.showUserMenu();
            } else if (userLogged.getUserType().equals(UserType.ADMIN.getCode())) {
                UIAdmin.showAdminMenu();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

            showMenu();
        }
    }
}

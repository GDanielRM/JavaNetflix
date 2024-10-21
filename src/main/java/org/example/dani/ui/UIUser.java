package org.example.dani.ui;

import org.example.dani.entity.Category;
import org.example.dani.entity.Director;
import org.example.dani.entity.Movie;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

import static org.example.dani.ui.UIMenu.em;

public class UIUser {
    public static void showUserMenu() {
        int response;
        do {
            System.out.println("\n\n--> User Menu <--");
            System.out.println("Welcome " + UIMenu.userLogged.getName());
            System.out.println("What do you want to see today?");
            System.out.println("1. Movies");
            System.out.println("2. Series");
            System.out.println("0. Logout");


            Scanner sc = new Scanner(System.in);
            response = Integer.parseInt(sc.nextLine());

            switch (response) {
                case 1:
                    response = 0;
                    showMoviesMenu();
                    break;
                case 2:
                    System.out.println("This feature was not developed, sorry.");
                    break;
                case 0:
                    System.out.println("Good bye " + UIMenu.userLogged.getName());
                    UIMenu.showMenu();
                    break;
                default:
                    System.out.println("Please select a correct answer");
                    break;
            }
        } while (response != 0);
    }

    private static void showMoviesMenu () {
        int response;
        do {
            System.out.println("\n\n--> Movies Actions <--");
            System.out.println("Select an option:");
            System.out.println("- Do you find a movie for: ");
            System.out.println("1. It's name");
            System.out.println("2. It's director");
            System.out.println("3. It's category");
            System.out.println("0. Go back");


            Scanner sc = new Scanner(System.in);
            response = Integer.parseInt(sc.nextLine());

            switch (response) {
                case 1:
                    response = 0;
                    findByName();
                    break;
                case 2:
                    response = 0;
                    findByDirector();
                    break;
                case 3:
                    response = 0;
                    findByCategory();
                    break;
                case 0:
                    showUserMenu();
                    break;
                default:
                    System.out.println("Please select a correct answer");
                    break;
            }
        } while (response != 0);
    }

    private static void findByName() {
        System.out.println("- Enter the name to search:");
        Scanner sc = new Scanner(System.in);
        String name = String.valueOf(sc.nextLine());

        TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m WHERE m.name LIKE :name",Movie.class);
        query.setParameter("name", "%" + name + "%");
        List<Movie> movies = query.getResultList();

        if (movies.isEmpty()) {
            System.out.println("- No movies with that name were found");
            showMoviesMenu();
            return;
        }

        System.out.println("-=-=- MOVIES FOUND -=-=-");
        movies.forEach(System.out::println);

        showMoviesMenu();
    }

    private static void findByDirector() {
        System.out.println("- Enter the name of the director of the film to search for:");
        Scanner sc = new Scanner(System.in);
        String name = String.valueOf(sc.nextLine());

        TypedQuery<Director> queryDirector = em.createQuery("SELECT d FROM Director d WHERE d.name LIKE :name",Director.class);
        queryDirector.setParameter("name", "%" + name + "%");
        List<Director> directors = queryDirector.getResultList();

        if (directors.isEmpty()) {
            System.out.println("- No movies with that director were found");
            showMoviesMenu();
            return;
        }

        Director director =  directors.get(0);

        TypedQuery<Movie> queryMovie = em.createQuery("SELECT m FROM Movie m WHERE m.directorId = :directorId",Movie.class);
        queryMovie.setParameter("directorId",director.getId());
        List<Movie> movies = queryMovie.getResultList();

        System.out.println("-=-=- MOVIES FOUND -=-=-");
        movies.forEach(System.out::println);

        showMoviesMenu();
    }

    private static void findByCategory() {
        System.out.println("- Enter the name category of the film to search for:");
        Scanner sc = new Scanner(System.in);
        String name = String.valueOf(sc.nextLine());

        TypedQuery<Category> queryCategory = em.createQuery("SELECT c FROM Category c WHERE c.name LIKE :name",Category.class);
        queryCategory.setParameter("name", "%" + name + "%");
        List<Category> categories = queryCategory.getResultList();

        if (categories.isEmpty()) {
            System.out.println("- No movies with that category were found");
            showMoviesMenu();
            return;
        }

        Category category = categories.get(0);

        TypedQuery<Movie> queryMovie = em.createQuery("SELECT m FROM Movie m WHERE m.categoryId = :categoryId",Movie.class);
        queryMovie.setParameter("categoryId",category.getId());
        List<Movie> movies = queryMovie.getResultList();

        System.out.println("-=-=- MOVIES FOUND -=-=-");
        movies.forEach(System.out::println);

        showMoviesMenu();
    }
}

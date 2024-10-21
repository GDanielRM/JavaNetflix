package org.example.dani.ui.menu;

import org.example.dani.dao.impl.MovieImp;
import org.example.dani.dao.interfaces.IDao;
import org.example.dani.entity.Movie;
import org.example.dani.ui.UIAdmin;

import java.sql.SQLException;
import java.util.Scanner;

public class MovieMenu {
    public static void showMovieActionsMenu() throws SQLException {
        int response;
        do {
            System.out.println("\n\n");
            System.out.println("--> Movie Actions <--");
            System.out.println("Select an option:");
            System.out.println("1. List all movies");
            System.out.println("2. Create a new movie");
            System.out.println("3. Update an movie");
            System.out.println("4. Delete an movie");
            System.out.println("0. Go back");


            Scanner sc = new Scanner(System.in);
            response = Integer.parseInt(sc.nextLine());

            switch (response) {
                case 1:
                    response = 0;
                    listAllMovies();
                    break;
                case 2:
                    response = 0;
                    createNewMovies();
                    break;
                case 3:
                    response = 0;
                    movieUpdate();
                    break;
                case 4:
                    response = 0;
                    deleteMovie();
                    break;
                case 0:
                    UIAdmin.showAdminMenu();
                    break;
                default:
                    System.out.println("Please select a correct answer");
                    break;
            }
        } while (response != 0);
    }

    private static void listAllMovies() throws SQLException {
        System.out.println("\n");
        System.out.println("-=-=- MOVIE LIST -=-=-");
        IDao<Movie> movieIDao = new MovieImp();
        movieIDao.findAll().forEach(System.out::println);

        showMovieActionsMenu();
    }

    private static void createNewMovies() {
        try {
            System.out.println("\n-=-=- CREATE A NEW MOVIE -=-=-");

            Movie newMovie = new Movie();

            Scanner sc = new Scanner(System.in);

            System.out.println("- Enter the director name:");
            newMovie.setName(String.valueOf(sc.nextLine()));

            System.out.println("- Enter the Year of release of the movie:");
            newMovie.setYear(String.valueOf(sc.nextLine()));

            System.out.println("- Enter the category of the movie:");
            newMovie.setCategoryId(Integer.valueOf(sc.nextLine()));

            System.out.println("- Enter the director of the movie:");
            newMovie.setDirectorId(Integer.valueOf(sc.nextLine()));

            System.out.println("- Enter a synopsis from the movie:");
            newMovie.setSynopsis(String.valueOf(sc.nextLine()));

            IDao<Movie> movieIDao = new MovieImp();
            movieIDao.save(newMovie);

            System.out.println("Movie successfully created");
            showMovieActionsMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void movieUpdate() {
        try {
            System.out.println("\n-=-=- UPDATE A MOVIE -=-=-");

            Scanner sc = new Scanner(System.in);

            System.out.println("- Enter the ID of the movie to update:");
            int movieId = Integer.parseInt(sc.nextLine());

            IDao<Movie> movieIDao = new MovieImp();
            Movie existingMovie = movieIDao.getById(movieId);

            if (existingMovie == null) {
                System.out.println("Movie not found");
                showMovieActionsMenu();
                return;
            }

            System.out.println("- Current name: " + existingMovie.getName());
            System.out.println("- Enter a new name (or press Enter to keep the current):");
            String newName = sc.nextLine();
            if (!newName.isEmpty()) {
                existingMovie.setName(newName);
            }

            System.out.println("- Current year of release of the movie: " + existingMovie.getYear());
            System.out.println("- Enter new year of release of the movie (or press Enter to keep the current):");
            String newYear = sc.nextLine();
            if (!newYear.isEmpty()) {
                existingMovie.setYear(newYear);
            }

            System.out.println("- Currently this category of the movie is: " + existingMovie.getCategoryId());
            System.out.println("- Enter new value (or press Enter to keep the current):");
            String newCategoryId = sc.nextLine();
            if (!newCategoryId.isEmpty()) {
                existingMovie.setCategoryId(Integer.parseInt(newCategoryId));
            }

            System.out.println("- Currently this director of the movie is: " + existingMovie.getDirectorId());
            System.out.println("- Enter new value (or press Enter to keep the current):");
            String newDirectorId = sc.nextLine();
            if (!newDirectorId.isEmpty()) {
                existingMovie.setDirectorId(Integer.parseInt(newDirectorId));
            }

            System.out.println("- Currently this synopsis of the movie is: " + existingMovie.getSynopsis());
            System.out.println("- Enter new value (or press Enter to keep the current):");
            String newSynopsis = sc.nextLine();
            if (!newSynopsis.isEmpty()) {
                existingMovie.setSynopsis(newSynopsis);
            }

            movieIDao.save(existingMovie);

            System.out.println("Movie successfully updated");
            showMovieActionsMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteMovie() {
        try {
            System.out.println("\n-=-=- DELETE A MOVIE -=-=-");
            System.out.println("- Enter movie ID to delete: ");
            Scanner sc = new Scanner(System.in);
            int movieId = Integer.parseInt(sc.nextLine());

            IDao<Movie> movieIDao = new MovieImp();
            movieIDao.delete(movieId);

            System.out.println("Movie successfully deleted");
            showMovieActionsMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

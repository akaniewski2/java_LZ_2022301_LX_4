package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.arkani.LZ_2022301_LX.model.Movie;
import pl.arkani.LZ_2022301_LX.model.Movie;
import pl.arkani.LZ_2022301_LX.repo.MovieRepo;
import pl.arkani.LZ_2022301_LX.repo.MovieRepo;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/arkani2/")
public class MovieController {


    private MovieRepo movieRepo;
    @Autowired
    public MovieController(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    @GetMapping("movie/add")
    public String showSignUpForm(Movie movie) {
        return "movie/movie-add";
    }
    
    @PostMapping("movie/add")
    public String addMovie(@Valid Movie movie, BindingResult result, Model model) {
        if (result.hasErrors() || movie.getName().isBlank() ) {
            System.out.println("1");
            return "movie/movie-add"; // lokalizacja html
        }
        System.out.println("2");
        movieRepo.save(movie);
        System.out.println("3");
        return "redirect:/arkani2/movies"; //redirect przekierowuje na url
    }

    // additional CRUD methods
    @GetMapping("movies")
    public String showMovieList(Model model) {
        model.addAttribute("movies", movieRepo.findAll());
        return "movie/movies";
    }

    @GetMapping("movie/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Movie movie = movieRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie Id:" + id));

        model.addAttribute("movie", movie);
        return "movie/movie-update";
    }

    @PostMapping("movie/update/{id}")
    public String updateMovie(@PathVariable("id") long id, @Valid Movie movie,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            movie.setId(id);
            return "movie/movie-update";
        }

        movieRepo.save(movie);
        return "redirect:/arkani2/movies";
    }

    @GetMapping("movie/delete/{id}")
    public String deleteMovie(@PathVariable("id") long id, Model model) {
        Movie movie = movieRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie Id:" + id));
        movieRepo.delete(movie);
        return "redirect:/arkani2/movies";
    }
}
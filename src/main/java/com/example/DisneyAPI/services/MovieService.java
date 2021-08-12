package com.example.DisneyAPI.services;

import com.example.DisneyAPI.models.MovieModel;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.DisneyAPI.Repository.IMovieRepository;
import com.example.DisneyAPI.dto.MovieDto;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class MovieService {

    @Autowired
    IMovieRepository movieRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<MovieDto> getMovies(Map< String, Object> filterBy) {
        List<MovieModel> movieBD;
        movieBD = new ArrayList();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<MovieModel> movie = criteriaQuery.from(MovieModel.class);
        
        //Probando orders
        List<Order> orderList = new ArrayList();

        //Constructing list of parameters
        List<Predicate> predicates;
        predicates = new ArrayList();
        
        if(filterBy.containsKey("order")){
            if("ASC".equals(filterBy.get("order"))){
                System.out.println("ORDEN ASCENDENTE");
                orderList.add(criteriaBuilder.asc(movie.get("fechaCreacion")));
            } else {
                orderList.add(criteriaBuilder.desc(movie.get("fechaCreacion")));
            }
        }

        if (filterBy.containsKey("nombreMovie")) {
            predicates.add(criteriaBuilder.equal(movie.get("titulo"), filterBy.get("nombreMovie")));
        }

        if (filterBy.containsKey("genre")) {
            predicates.add(criteriaBuilder.equal(movie.get("genero"), filterBy.get("genre")));
        }

        System.out.println("PREDICATES" + predicates);

        criteriaQuery.select(movie)
                .where(predicates.toArray(new Predicate[]{}))
                .orderBy(orderList);

        movieBD = entityManager.createQuery(criteriaQuery).getResultList();

        List<MovieDto> movieDto = new ArrayList();

        movieBD.forEach(movieEnt -> {
            movieDto.add(MovieDto.getFiltered(movieEnt));
        });

        return movieDto;
    }

    @Transactional
    public MovieDto saveMovie(MovieDto movieDto) {
        MovieModel movieBD = movieDto.converToEntity(movieDto);
        this.movieRepository.save(movieBD);

        movieDto.setIdMovie(movieDto.getIdMovie());

        movieDto.setIdMovie(movieBD.getIdMovie());

        return movieDto;
    }

    @Transactional
    public boolean deleteMovie(Long id) {
        try {
            this.movieRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public MovieDto updateMovie(Long id, MovieDto movie) {
        MovieModel movieBD;
        try {
            movieBD = movieRepository.getById(id);

            System.out.println("paso por try");
            movieBD = MovieModel.builder()
                    .idMovie(id)
                    .imagen(movie.getImagen())
                    .titulo(movie.getTitulo())
                    .fechaCreacion(movie.getFechaCreacion())
                    .calificacion(movie.getCalificacion())
                    .genero(movie.getGenero())
                    //.personajesAsociados(movie.getPersonajesAsociados())
                    .build();

            movieBD = movieRepository.saveAndFlush(movieBD);

            System.out.println("PASE POR ACA");

        } catch (EntityNotFoundException e) {
            movieBD = movieRepository.saveAndFlush(movie.converToEntity(movie));
            System.out.println("PASE POR CATCH");
        }

        return MovieDto.converToDto(movieBD);
    }
}

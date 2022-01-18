package org.generation.italy.repo;

import org.generation.italy.model.Ingredienti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IngredientiRepo extends JpaRepository<Ingredienti, Integer> {

}

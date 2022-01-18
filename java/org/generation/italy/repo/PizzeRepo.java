package org.generation.italy.repo;

import java.util.List;

import org.generation.italy.model.Pizze;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PizzeRepo extends JpaRepository<Pizze, Integer> {
	List<Pizze> findByNameContainingIgnoreCaseOrderByName(String keyword);
}

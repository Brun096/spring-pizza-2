package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Ingredienti;
import org.generation.italy.repo.IngredientiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class IngredientiService {


	@Autowired
	private IngredientiRepo repo;
	
	public List<Ingredienti> findAllSortByIngrediants(){
		return repo.findAll(Sort.by("ingrediants"));
	}
}

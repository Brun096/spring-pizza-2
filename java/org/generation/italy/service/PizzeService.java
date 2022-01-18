package org.generation.italy.service;


import java.util.List;


import org.generation.italy.model.Pizze;
import org.generation.italy.repo.PizzeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PizzeService {

	@Autowired
	private PizzeRepo repository;
	
	
	public List<Pizze> findAllSortedByName() {
		return repository.findAll(Sort.by("name"));
	}

	public List<Pizze> findByKeywordSortedByName(String keyword) {
		return repository.findByNameContainingIgnoreCaseOrderByName(keyword);
	}

	public Pizze create(Pizze pizze) {

		return repository.save(pizze);
	}

	public Pizze getById(Integer id) {
		return repository.getById(id);
	}
	
	
	public Pizze update(Pizze pizze) {
		
		return repository.save(pizze);
	}
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
}

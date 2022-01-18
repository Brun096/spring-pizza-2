package org.generation.italy.model;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Pizze {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@NotNull
	@NotEmpty(message="Nome obbligatorio")
	private String name;
	@Lob
	private String description;
	@NotNull
	@Min(value=0)
	private int price;
	@ManyToMany
	private List<Ingredienti> ingrediants;
	//GETTER and SETTER
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<Ingredienti> getIngrediants() {
		return ingrediants;
	}
	public void setIngrediants(List<Ingredienti> ingrediants) {
		this.ingrediants = ingrediants;
	}
	public String ingredientiToString() {
		List<String> ingredientiString = new ArrayList<>();
		for(Ingredienti i : ingrediants) {
			ingredientiString.add(i.getIngrediants());
		}
		return String.join(", ", ingredientiString);
	}
	
}

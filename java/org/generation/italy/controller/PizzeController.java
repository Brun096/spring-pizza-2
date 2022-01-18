package org.generation.italy.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.italy.model.Pizze;
import org.generation.italy.service.IngredientiService;
import org.generation.italy.service.PizzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pizze")
public class PizzeController {

	@Autowired
	private PizzeService service;

	@Autowired
	private IngredientiService ingService;
	
	

	@GetMapping
	public String menu (Model model, @RequestParam(name="keyword", required=false) String keyword) {
		List<Pizze> result;
		if(keyword != null && !keyword.isEmpty()) {
			result = service.findByKeywordSortedByName(keyword);
			model.addAttribute("keyword", keyword);
		}else {
			result = service.findAllSortedByName();
		}
		model.addAttribute("list", result);
		return "/pizze/list";
	}
	
	
	
	
	@GetMapping("/detail/{id}")//leggiamo una variabile dalla path id
	public String detail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("pizze", service.getById(id));
		return "/pizze/detail";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("pizze", new Pizze());
		model.addAttribute("ingredienteList", ingService.findAllSortByIngrediants());
		return "/pizze/edit";

	}

	@PostMapping("/create") // ci permette di ricevere i dati
	public String doCreate(@Valid @ModelAttribute("pizze") Pizze formPizza, BindingResult bidingReasult, Model model) {

		if (bidingReasult.hasErrors()) {
			model.addAttribute("edit", false);
			model.addAttribute("ingredienteList", ingService.findAllSortByIngrediants());
			return "/pizze/edit";
		}
		// else salva i dati
		service.create(formPizza); // poi dobbiamo ritornare la lista
		return "redirect:/pizze";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("pizze", service.getById(id));
		model.addAttribute("ingredienteList", ingService.findAllSortByIngrediants());
		return "/pizze/edit";
	}

	@PostMapping("/edit/{id}")
	public String doUpdate(@Valid @ModelAttribute("pizze") Pizze formPizza, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
			model.addAttribute("ingredienteList", ingService.findAllSortByIngrediants());
			return "/pizze/edit";
		}
		service.update(formPizza);
		return "redirect:/pizze";
	}
	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		service.deleteById(id);
		redirectAttributes.addFlashAttribute("successMessage", "Pizza eliminata!");
		return "redirect:/pizze";
	}
}

package com.unitbv.lab.lab5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.unitbv.lab.lab5.persistence.model.Person;
import com.unitbv.lab.lab5.persistence.repository.PersonRepository;

@Controller
public class PersonController {

	@Autowired
	PersonRepository personRepository;

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String sayHello(@PathVariable String name) {
		return "Hello, " + name;
	}

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String getFullgreeting(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		return "Hello, " + firstName + " " + lastName + "!";
	}

	/**
	 * Creates the person.
	 *
	 * @param person the person
	 * @return the person
	 */
	@PostMapping("/add")
	public String add(@ModelAttribute Person person, Model m) {
		System.out.println(" person to save = "+ person.getFirstName());
		if (person.getId() == 0) {
			personRepository.save(person);
		} else {
			update(person);
		}
		
        m.addAttribute("person", new Person());
		List<Person> persons = personRepository.findAll();
		System.out.println(" persons = "+ persons.size());
		m.addAttribute("persons", persons);
		return "persons";
	}
	
	@GetMapping("/add")
	public String findAllPersons(Model model) {
		 System.out.println(" findAllPersons in db ");
		model.addAttribute("contact", new Person());
		List<Person> persons = personRepository.findAll();
		System.out.println(" persons = "+ persons.size());
		model.addAttribute("persons", persons);
		return "persons";
	}
	
	/**
	 * Edits the person.
	 *
	 * @param person the person
	 * @return the person
	 */
	public void update(Person p) {
		System.out.println(" Update called with ID = "+ p.getId());
		personRepository.save(p);
	}
	
	@RequestMapping("/edit")
	public String edit(@RequestParam("id") Long id) {
		System.out.println(" Edit called with ID = "+ id);
		Person p = personRepository.findOne(id); 
		personRepository.save(p);

		return "person";
	}

	@RequestMapping("/findAll")
	public String findAll(Model model) {
		model.addAttribute("person", new Person());
		List<Person> persons = personRepository.findAll();
		System.out.println(" persons = "+ persons.size());
		model.addAttribute("persons", persons);
		return "persons";
	}
	
	@RequestMapping("/findbyid")
	public String findById(@RequestParam("id") Long id) {
		String result = "";
		result = personRepository.findOne(id).toString();
		return result;
	}

	/**
	 * Delete person.
	 *
	 * @param person the person
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") Long id, Model model) {
		System.out.println(" Delete called with ID = "+ id);
		Person c = personRepository.findOne(id); 
		personRepository.delete(c);

		
		model.addAttribute("person", new Person());
		List<Person> persons = personRepository.findAll();
		System.out.println(" persons = "+ persons.size());
		model.addAttribute("persons", persons);
		return "persons";
	}
}

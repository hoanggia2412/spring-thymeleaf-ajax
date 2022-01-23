package com.ezbytes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;

@Controller
@RequestMapping("/")
public class DemoController {
	private List<Obj> models = new ArrayList<>();
	@PostConstruct
	private void init() {
		Obj obj1 = Obj.builder().name("TR").code("012").number(5).range("5m").block("B01").build();
		Obj obj2 = Obj.builder().name("TC").code("013").number(2).build();
		Obj obj3 = Obj.builder().name("DF").code("014").number(3).range("11m").block("C01").build();
		Obj obj4 = Obj.builder().name("WE").code("015").number(4).range("7m").build();
		Obj obj5 = Obj.builder().name("GG").code("016").number(9).block("E01").build();
		//models = Arrays.asList(obj1,obj2,obj3,obj4,obj5);
		models.add(obj1);
		models.add(obj2);
		models.add(obj3);
		models.add(obj4);
		models.add(obj5);
	}
	
	@GetMapping
	public String renderMain(Model model) {
		model.addAttribute("models", models);
		return "home";
	}
	@GetMapping("/{code}")
	public String get(@PathVariable String code, Model model) {
		Obj ojb = models.stream().filter(item -> item.getCode().equals(code)).findFirst()
				.orElse(Obj.builder().name("TC").code("013").number(2).build());
		model.addAttribute("model",ojb);		
		return "components/details :: details";		
	}
	
	@GetMapping("/register")
	public String renderRegister(Model model) {
		
		return "components/model_";
	}
	
	@PostMapping("/register")
	public String renderRegister(Obj request,Model model) {
		System.out.println(request);
		models.add(request);
		return "redirect:/";
	}
	
	@Getter
	@Builder
	static class Obj implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String name;
		private String code;
		private Integer number;
		@Default
		private boolean status = true;
		@Default
		private Integer height = 0;
		@Default
		private Integer width = 5;
		@Default
		private Integer weight = 4;
		@Default
		private String range = "4m";
		@Default
		private String block = "A04";
	}
}


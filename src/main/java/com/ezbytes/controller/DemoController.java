package com.ezbytes.controller;

import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.Getter;

@Controller
@RequestMapping
public class DemoController {
	List<Obj> models =null;
	@PostConstruct
	private void init() {
		Obj obj1 = Obj.builder().name("TR").code("012").number(5).range("5m").block("B01").build();
		Obj obj2 = Obj.builder().name("TC").code("013").number(2).build();
		Obj obj3 = Obj.builder().name("DF").code("014").number(3).range("11m").block("C01").build();
		Obj obj4 = Obj.builder().name("WE").code("015").number(4).range("7m").build();
		Obj obj5 = Obj.builder().name("GG").code("016").number(9).block("E01").build();
		models = Arrays.asList(obj1,obj2,obj3,obj4,obj5);
	}
	
	@GetMapping
	public String renderMain(Model model) {
		model.addAttribute("models", models);
		return "home";
	}
	@GetMapping("{code}")
	public String get(@PathVariable String code, Model model) {
		Obj ojb = models.stream().filter(item -> item.getCode().equals(code)).findFirst()
				.orElse(Obj.builder().name("TC").code("013").number(2).build());
		model.addAttribute("model",ojb);		
		return "components/details :: details";		
	}
	
	@GetMapping("register")
	public String renderRegister(Model model) {
		
		return "components/model_";
	}
	
	@PostMapping("register")
	public ResponseEntity<?> renderRegister(@RequestBody Obj request,Model model) {
		models.add(request);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/register").toUriString());
		return ResponseEntity.created(uri).body(request);
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
		private int number;
		@Default
		private boolean status = true;
		@Default
		private int height = 0;
		@Default
		private int width = 5;
		@Default
		private int weight = 4;
		@Default
		private String range = "4m";
		@Default
		private String block = "A04";
	}
}


package com.deli.controllers;

import com.deli.services.ProductManagementService;
import com.deli.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class RestController {

	@Autowired
	private ProductManagementService productManagementService;

        @RequestMapping(value = "/index", method = RequestMethod.GET)
        public String index()
        {
            return "index";
        }

		@GetMapping({
				"/",
				"/home"
		})
		public String hello(Model model) {
			List<Product> productList= productManagementService.getAllProducts();
			model.addAttribute("productList", productList);
			return "homepage";
		}
}

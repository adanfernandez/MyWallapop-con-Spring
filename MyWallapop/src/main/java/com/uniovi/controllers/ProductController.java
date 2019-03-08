package com.uniovi.controllers;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Product;
import com.uniovi.entities.User;
import com.uniovi.services.ProductsService;
import com.uniovi.services.UsersService;

@Controller
public class ProductController {
	
	@Autowired
	private HttpSession httpSession;

	@Autowired // Inyectar el servicio
	private ProductsService productsService;

	@Autowired
	private UsersService usersService;

	@RequestMapping("/product/myProducts")
	public String getMyProducts(Model model, Principal principal) {
		
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		
		model.addAttribute("productList", productsService.getProductsForUser(user));
		model.addAttribute("user", user);
		return "product/myProducts";
	}
	
	@RequestMapping("/product/list")
	public String getList(Model model, Principal principal, @RequestParam(value = "", required=false) String searchText) {
		model.addAttribute("user", usersService.getUserByEmail(principal.getName()));
		
		if (searchText != null && !searchText.isEmpty()) {
			model.addAttribute("productList", productsService.searchProductsByTitle(searchText));
		}
		else {
			model.addAttribute("productList", productsService.getProducts());
		}
		return "product/list";
	}

	@RequestMapping(value = "/product/add")
	public String getProduct(Model model, Principal principal) {
		//model.addAttribute("usersList", usersService.getUsers());
		model.addAttribute("user", usersService.getUserByEmail(principal.getName()));
		return "product/add";
	}

	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public String setProduct(@ModelAttribute Product product, Principal principal) {
		
		User user = usersService.getUserByEmail(principal.getName());
		product.setUser(user);
		productsService.addProduct(product, user);
		return "redirect:/product/list";
	}

	@RequestMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id, Principal principal) {
		User user = usersService.getUserByEmail(principal.getName());
		
		productsService.deleteProduct(id, user);
		return "redirect:/product/myProducts";
	}

	@RequestMapping("/product/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("product", productsService.getProduct(id));
		return "product/details";
	}

	/**
	 * Vista GET.
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/product/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("product", productsService.getProduct(id));
		model.addAttribute("usersList", usersService.getUsers());
		return "product/edit";
	}

	/**
	 * Petición POST.
	 * 
	 * @param model
	 * @param id
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/product/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Product product, Principal principal) {
		Product original = productsService.getProduct(id);

		original.setTitle(product.getTitle());
		original.setPrice(product.getPrice());
		original.setDescription(product.getDescription());
		
		User user = usersService.getUserByEmail(principal.getName());
		
		productsService.addProduct(original, user);
		return "redirect:/product/details/" + id;
	}


}

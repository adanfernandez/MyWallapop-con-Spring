package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Product;
import com.uniovi.entities.User;
import com.uniovi.services.ProductsService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddProductFormValidator;

@Controller
public class ProductController {

	@Autowired // Inyectar el servicio
	private ProductsService productsService;

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private AddProductFormValidator addProductFormValidator;

	@RequestMapping("/product/myProducts")
	public String getMyProducts(Model model, Principal principal) {

		String email = principal.getName();
		User user = usersService.getUserByEmail(email);

		model.addAttribute("productList", productsService.getProductsForUser(user));
		model.addAttribute("user", user);
		return "product/myProducts";
	}

	@RequestMapping("/product/list")
	public String getList(Model model, Pageable pageagle, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {

		
		Page<Product> products = new PageImpl<Product>(new LinkedList<Product>());
		model.addAttribute("user", usersService.getUserByEmail(principal.getName()));

		
		if (searchText != null && !searchText.isEmpty()) {
			products = productsService.searchProductsByTitle(pageagle, searchText);
		} else {
			products = productsService.getProducts(pageagle);
		}
		model.addAttribute("page", products);
		model.addAttribute("productList", products.getContent());
		return "product/list";
	}

	@RequestMapping(value = "/product/add")
	public String getProduct(Model model, Principal principal) {
		model.addAttribute("product", new Product());
		model.addAttribute("user", usersService.getUserByEmail(principal.getName()));
		return "product/add";
	}

	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public String setProduct(@Validated @ModelAttribute Product product, Principal principal, BindingResult result, Model model) {
		model.addAttribute("user", usersService.getUserByEmail(principal.getName()));
		addProductFormValidator.validate(product, result);
		if (result.hasErrors()) {
			return "/product/add";
		}
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

	@RequestMapping("/product/list/update")
	public String updateList(Model model, Principal principal, Pageable pageable) {
		User user = usersService.getUserByEmail(principal.getName());
		Page<Product> products = productsService.getProducts(pageable);
		model.addAttribute("productList", products.getContent());
		model.addAttribute(user);
		//model.addAttribute("page", products);
		return "product/list :: divProducts";
	}

	@RequestMapping("/product/{id}/buy")
	public String buyProduct(Model model, @PathVariable Long id, Principal principal, Pageable pageable) {
		User user = usersService.getUserByEmail(principal.getName());
		Page<Product> products = productsService.getProducts(pageable);

		productsService.buy(id, user);
		model.addAttribute(user);
		model.addAttribute("page", products);
		System.out.println(model.containsAttribute("page"));
		return "product/list";
	}
	
	@RequestMapping("/product/purchased")
	public String getPurchased(Model model, Principal principal)
	{
		User user = usersService.getUserByEmail(principal.getName());
		model.addAttribute("productList", productsService.getProductsPurchased(user));
		model.addAttribute("user", user);
		return "product/purchased";
	}
}
package com.uniovi.controllers;

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
import com.uniovi.services.ProductsService;
import com.uniovi.services.UsersService;

@Controller
public class ProductController {

	@Autowired // Inyectar el servicio
	private ProductsService productsService;

	@Autowired
	private UsersService usersService;

	@RequestMapping("/product/list")
	public String getList(Model model) {
		model.addAttribute("productList", productsService.getProducts());
		return "product/list";
	}

	@RequestMapping(value = "/product/add")
	public String getProduct(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		return "product/add";
	}

	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public String setProduct(@ModelAttribute Product product) {
		productsService.addProduct(product);
		return "redirect:/product/list";
	}

	@RequestMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productsService.deleteProduct(id);
		return "redirect:/product/list";
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
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Product product) {
		Product original = productsService.getProduct(id);
		// modificar solo score y description
		original.setPrice(product.getPrice());
		original.setDescription(product.getDescription());
		productsService.addProduct(original);
		return "redirect:/product/details/" + id;
	}

	/**
	 * Actualizar pagina de productos
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/product/list/update")
	public String updateList(Model model) {
		model.addAttribute("productList", productsService.getProducts());
		return "product/list :: tableProducts";
	}

}

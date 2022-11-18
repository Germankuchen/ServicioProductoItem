package com.ar.gk.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ar.gk.model.ItemProducto;
import com.ar.gk.model.Producto;
import com.ar.gk.services.ItemService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
public class ProductoItemController {

	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemServices;
	
	@Autowired
	private CircuitBreakerFactory cbFactory; 
	
	private final Logger log = LoggerFactory.getLogger(ProductoItemController.class);
	
	@GetMapping("/listar")
	public List<ItemProducto> findAll(@RequestParam(name = "nombre", required = false) String nombre, 
			@RequestHeader(name = "token-request", required = false) String tokenRequest) {
		if (nombre != null) {
			System.out.println("El request param es: " + nombre);
		}
		if (tokenRequest != null) {
			System.out.println("El request header es: " + tokenRequest);
		}
		return itemServices.findAll();
	}
	
	//@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/listar/{id}/cantidad/{cantidad}")
	public ItemProducto findById(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Integer cantidad) {
		return cbFactory.create("items").run(()-> itemServices.findByIdCantidad(id, cantidad), e -> metodoAlternativo(id, cantidad, e));
	}
	
	@GetMapping("/listar2/{id}/cantidad/{cantidad}")
	@CircuitBreaker(name="items", fallbackMethod = "metodoAlternativo")
	public ItemProducto findById2(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Integer cantidad) {
		return itemServices.findByIdCantidad(id, cantidad);
	}
	
	@GetMapping("/listar3/{id}/cantidad/{cantidad}")
	public ItemProducto findById3(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Integer cantidad) {
		return itemServices.findByIdCantidad(id, cantidad);
	}
	
	public ItemProducto metodoAlternativo(Long id, Integer cantidad, Throwable e) {
		log.error(e.getMessage());
		ItemProducto item = new ItemProducto();
		Producto producto = new Producto();
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Llanta");
		producto.setPrecio(1500.5);
		item.setProducto(producto);
		return item;
	}
	
}

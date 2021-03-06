package com.ar.gk.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ar.gk.model.ItemProducto;
import com.ar.gk.model.Producto;
import com.ar.gk.services.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ProductoItemController {

	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemServices;
	
	@GetMapping("/listar")
	public List<ItemProducto> findAll() {
		return itemServices.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/listar/{id}/cantidad/{cantidad}")
	public ItemProducto findById(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Integer cantidad) {
		return itemServices.findByIdCantidad(id, cantidad);
	}
	
	public ItemProducto metodoAlternativo(Long id, Integer cantidad) {
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

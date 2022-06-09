package com.ar.gk.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ar.gk.model.Producto;

@FeignClient(name="servicio-productos")
public interface ProductoClienteRest {

	@GetMapping("/listar")
	public List<Producto> listar();
	
	
	@GetMapping("/listar/{id}")
	public Producto obtenerByid(@PathVariable(name = "id") Long id);
	
}

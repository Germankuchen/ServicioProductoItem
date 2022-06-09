package com.ar.gk.services.implementation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ar.gk.model.ItemProducto;
import com.ar.gk.model.Producto;
import com.ar.gk.services.ItemService;

@Service
public class ItemServicesImpl implements ItemService {

	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	public List<ItemProducto> findAll() {
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar", Producto[].class));
		return productos.stream().map(producto -> new ItemProducto(producto, 1)).collect(Collectors.toList());
	}

	@Override
	public ItemProducto findByIdCantidad(Long id, Integer cantidad) {
		Producto producto = clienteRest.getForObject("http://localhost:8001/listar/" + id, Producto.class);
		return new ItemProducto(producto, cantidad);
	}

}

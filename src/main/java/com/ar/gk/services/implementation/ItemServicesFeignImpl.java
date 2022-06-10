package com.ar.gk.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ar.gk.feign.ProductoClienteRest;
import com.ar.gk.model.ItemProducto;
import com.ar.gk.model.Producto;
import com.ar.gk.services.ItemService;

@Service("serviceFeign")
@Primary
public class ItemServicesFeignImpl implements ItemService {

	@Autowired
	ProductoClienteRest productoClienteRest;
	
	@Override
	public List<ItemProducto> findAll() {
		List<Producto> productos = productoClienteRest.listar();
		return productos.stream().map(producto -> new ItemProducto(producto, 1)).collect(Collectors.toList());
	}

	@Override
	public ItemProducto findByIdCantidad(Long id, Integer cantidad) {
		Producto producto = productoClienteRest.obtenerByid(id);
		return new ItemProducto(producto, cantidad);
	}

}

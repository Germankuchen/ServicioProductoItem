package com.ar.gk.services;

import java.util.List;

import com.ar.gk.model.ItemProducto;

public interface ItemService {
	public List<ItemProducto> findAll();
	public ItemProducto findByIdCantidad(Long id, Integer cantidad);
	
	
}

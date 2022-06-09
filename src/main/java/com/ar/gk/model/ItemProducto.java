package com.ar.gk.model;

public class ItemProducto {

	private Producto producto;
	private Integer cantidad = 1;
	
	public ItemProducto() {
		
	}
	
	public ItemProducto(Producto producto, Integer cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getTotal( ) {
		return this.producto.getPrecio() * this.cantidad.doubleValue();
	}
	
	
	
}

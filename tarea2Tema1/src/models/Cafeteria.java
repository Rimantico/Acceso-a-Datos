package models;

import java.io.Serializable;

public class Cafeteria implements Serializable{
	
	private String producto;
	private String categoria;
	private double precio_euros;
	
	public Cafeteria(String producto,String categoria,double precio_euros) {
		this.producto = producto;
		this.categoria = categoria;
		this.precio_euros = precio_euros;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrecio_euros() {
		return precio_euros;
	}

	public void setPrecio_euros(double precio_euros) {
		this.precio_euros = precio_euros;
	}

	@Override
	public String toString() {
		return "Cafeteria [producto=" + producto + ", categoria=" + categoria + ", precio_euros=" + precio_euros + "]";
	}
	
	
	

}

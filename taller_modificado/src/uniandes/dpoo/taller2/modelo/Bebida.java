package uniandes.dpoo.taller2.modelo;

public class Bebida implements Producto {
	
	private String nombre;
	private Integer precio;

	public Bebida(String nombre, Integer precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}

	@Override
	public Integer getPrecio() {
		return this.precio;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String generarTextoFactura() {
		return this.nombre + "   " + this.precio;
	}

}

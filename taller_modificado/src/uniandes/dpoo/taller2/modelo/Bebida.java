package uniandes.dpoo.taller2.modelo;

public class Bebida implements Producto {


	private String nombre;
	private Integer precio;
	private Integer calorias;

	public Bebida(String nombre, Integer precio, Integer calorias) {
		this.nombre = nombre;
		this.precio = precio;
		this.calorias = calorias;
	}

	@Override
	public Integer getCalorias() {
		return this.calorias;
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

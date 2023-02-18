package uniandes.dpoo.taller2.modelo;

public class ProductoMenu implements Producto {
	
	private String nombre;
	private Integer precioBase;

	public ProductoMenu(String nombre, Integer precioBase) {
		super();
		this.nombre = nombre;
		this.precioBase = precioBase;
	}

	@Override
	public Integer getPrecio() {
		return this.precioBase;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String generarTextoFactura() {
		return nombre + "   " + precioBase;
	}

}

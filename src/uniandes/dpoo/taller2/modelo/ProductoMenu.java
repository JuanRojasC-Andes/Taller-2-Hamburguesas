package uniandes.dpoo.taller2.modelo;

public class ProductoMenu implements Producto {
	
	private String nombre;
	private int precioBase;

	public ProductoMenu(String nombre, int precioBase) {
		super();
		this.nombre = nombre;
		this.precioBase = precioBase;
	}

	@Override
	public int getPrecio() {
		return this.precioBase;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		return null;
	}

}

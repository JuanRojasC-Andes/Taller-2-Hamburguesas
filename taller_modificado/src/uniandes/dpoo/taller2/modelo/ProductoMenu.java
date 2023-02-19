package uniandes.dpoo.taller2.modelo;

public class ProductoMenu implements Producto {

	private String nombre;
	private Integer precioBase;
	private Integer calorias;
	
	public ProductoMenu(String nombre, Integer precioBase, Integer calorias) {
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.calorias = calorias;
	}

	@Override
	public Integer getCalorias() {
		return this.calorias;
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
	
	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().equals(ProductoMenu.class)) return false;
		ProductoMenu pm = (ProductoMenu) obj;
		if (pm.getNombre() == this.nombre && pm.getPrecio() == this.precioBase && pm.getCalorias() == this.calorias) {
			return true;
		}
		return false;
 	}
}

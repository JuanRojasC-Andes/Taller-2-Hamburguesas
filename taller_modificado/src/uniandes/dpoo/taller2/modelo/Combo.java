package uniandes.dpoo.taller2.modelo;

import java.util.ArrayList;

public class Combo implements Producto{
	
	private Double descuento;
	private String nombre;
	private ArrayList<ProductoMenu> itemsCombo;

	public Combo(Double descuento, String nombre) {
		this.descuento = descuento;
		this.nombre = nombre;
		this.itemsCombo = new ArrayList<ProductoMenu>();
	}
	
	public void agregarItemACombo(Producto itemCombo) {
		this.itemsCombo.add((ProductoMenu) itemCombo);
	}

	@Override
	public Integer getPrecio() {
		int total = 0;
		for (Producto p : this.itemsCombo) {
			total += p.getPrecio();
		}
		return (int) (total * (1 - this.descuento));
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String generarTextoFactura() {
		String texto = this.nombre + "   " + getPrecio();
		for (ProductoMenu pm : itemsCombo) {
			texto += "\n * " + pm.getNombre();
		}
		return texto;
	}

	@Override
	public Integer getCalorias() {
		int calorias = 0;
		for (Producto p : this.itemsCombo) {
			calorias += p.getCalorias();
		}
		return calorias;
	}

}

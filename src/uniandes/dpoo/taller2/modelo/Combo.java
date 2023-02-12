package uniandes.dpoo.taller2.modelo;

import java.util.ArrayList;

public class Combo implements Producto{
	
	private double discount;
	private String nombre;
	private ArrayList<Producto> itemsCombo;

	public Combo(double discount, String nombre) {
		super();
		this.discount = discount;
		this.nombre = nombre;
		this.itemsCombo = new ArrayList<Producto>();
	}
	
	public void agregarItemACombo(Producto itemCombo) {
		this.itemsCombo.add((ProductoMenu) itemCombo);
	}

	@Override
	public int getPrecio() {
		double total = 0.0;
		for (Producto p : this.itemsCombo) {
			total += p.getPrecio();
		}
		return (int) (total * (1 - this.discount));
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

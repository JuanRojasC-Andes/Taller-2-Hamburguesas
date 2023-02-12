package uniandes.dpoo.taller2.modelo;

import java.util.ArrayList;

public class Combo implements Producto{
	
	private double discount;
	private String nombre;
	private ArrayList<ProductoMenu> itemsCombo;

	public Combo(double discount, String nombre) {
		super();
		this.discount = discount;
		this.nombre = nombre;
		this.itemsCombo = new ArrayList<ProductoMenu>();
	}
	
	public void agregarItemACombo(Producto itemCombo) {
		this.itemsCombo.add((ProductoMenu) itemCombo);
	}

	@Override
	public int getPrecio() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		return null;
	}

}

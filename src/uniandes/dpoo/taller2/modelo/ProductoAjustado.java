package uniandes.dpoo.taller2.modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto {
	
	private ProductoMenu base;
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;
	
	public ProductoAjustado(ProductoMenu base) {
		super();
		this.base = base;
		this.agregados = new ArrayList<Ingrediente>();
		this.eliminados = new ArrayList<Ingrediente>();
	}
	
	public void agregarIngrediente(Ingrediente i) {
		this.agregados.add(i);
	}
	
	public void eliminarIngrediente(Ingrediente i) {
		this.eliminados.add(i);
	}

	@Override
	public int getPrecio() {
		int precio = base.getPrecio();
		for (Ingrediente a : agregados) {
			precio += a.getCostoAdicional();
		}
		return precio;
	}

	@Override
	public String getNombre() {
		return this.base.getNombre();
	}

	@Override
	public String generarTextoFactura() {
		String texto = this.base.generarTextoFactura();
		for (Ingrediente a : agregados) {
			texto += "\n + " + a.generarTextoFactura();
		}
		for (Ingrediente a : eliminados) {
			texto += "\n - " + a.generarTextoFactura();
		}
		return texto;
	}

}

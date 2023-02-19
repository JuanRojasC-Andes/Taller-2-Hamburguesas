package uniandes.dpoo.taller2.modelo;

public class Ingrediente {
	
	private String nombre;
	private Integer costoAdicional;
	private Integer calorias;

	public Ingrediente(String nombre, Integer costoAdicional, Integer calorias) {
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
		this.calorias = calorias;
	}


	public Integer getCalorias() {
		return calorias;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getCostoAdicional() {
		return costoAdicional;
	}
	
	public String generarTextoFactura() {
		return this.nombre + " " + this.costoAdicional;
	}
}

package uniandes.dpoo.taller2.modelo;

public class Ingrediente {
	
	private String nombre;
	private Integer costoAdicional;
	
	public Ingrediente(String nombre, Integer costoAdicional) {
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
				
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

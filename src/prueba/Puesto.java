package prueba;

public class Puesto<T> {

	private String puesto;
	private T elemento;
	
	public Puesto(String puesto,T elemento) {
		this.puesto=puesto;
		this.elemento=elemento;
	}
	
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public T getElemento() {
		return elemento;
	}
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}
}

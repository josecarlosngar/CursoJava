package prueba;

import java.time.LocalDateTime;

public class LineaEntrada {

	public LineaEntrada() {}
	
	private LocalDateTime fecha;
	private String metodo;
	private String pagina;
	private String resultado;
	private Long tiempoRespuesta;
	
	

	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}


	public String getMetodo() {
		return metodo;
	}


	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}


	public String getPagina() {
		return pagina;
	}


	public void setPagina(String pagina) {
		this.pagina = pagina;
	}


	public String getResultado() {
		return resultado;
	}


	public void setResultado(String resultado) {
		this.resultado = resultado;
	}


	public Long getTiempoRespuesta() {
		return tiempoRespuesta;
	}


	public void setTiempoRespuesta(Long tiempoRespuesta) {
		this.tiempoRespuesta = tiempoRespuesta;
	}



	
}

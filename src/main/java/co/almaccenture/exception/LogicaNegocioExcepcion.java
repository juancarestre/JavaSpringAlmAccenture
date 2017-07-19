package co.almaccenture.exception;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class LogicaNegocioExcepcion extends Exception {

	private final transient Logger logger = Logger.getLogger(this.getClass());
	
	public LogicaNegocioExcepcion(String mensajeExcepcion) {
		super(mensajeExcepcion);
		logger.error(mensajeExcepcion);
	}
	
}

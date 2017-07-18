package co.almaccenture.exception;

import org.apache.log4j.Logger;

public class LogicaNegocioExcepcion extends Exception {

	private static final long serialVersionUID = -3336730301340927441L;
	Logger logger = Logger.getLogger(this.getClass());
	
	public LogicaNegocioExcepcion(String mensajeExcepcion) {
		super(mensajeExcepcion);
		logger.error(mensajeExcepcion);
	}
	
}

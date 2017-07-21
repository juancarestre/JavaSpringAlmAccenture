package co.almaccenture.exception;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class LogicaNegocioExcepcion extends Exception {

//	private final transient Logger logger = Logger.getLogger(this.getClass());
	Logger logger = Logger.getLogger(this.getClass());
	public LogicaNegocioExcepcion(String mensajeExcepcion) {
		super(mensajeExcepcion);
		logger.error(mensajeExcepcion);
	}
	public LogicaNegocioExcepcion(String message, Throwable cause) {
		super(message, cause);
		logger.error(message);
		// TODO Auto-generated constructor stub
	}
	public LogicaNegocioExcepcion(Throwable cause) {
		super(cause);
		logger.error(cause.getMessage());
		// TODO Auto-generated constructor stub
	}

	
}

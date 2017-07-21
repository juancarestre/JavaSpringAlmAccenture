package co.almaccenture.business;

import co.almaccenture.model.Caja;

public interface LogicaNegocioCaja {

	/**
	 * Obtiene una caja aleatoria, obteniendo de la lista de cajas.
	 * De Sara: Consulta todas las cajas existentes la base de datos y retorna una caja
	 * basado en un index generado aleatoriamente entre 0 y la cantidad de
	 * cajas.
	 * @return Caja seleccionada aleatoriamente
	 */
	public Caja generarCajaAleatoria();
}

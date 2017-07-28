package co.almaccenture.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.almaccenture.model.Caja;
/**
 * Interfaz que hereda de CRUDrepository, genera los metodos necesarios DAO 
 * @author Administrator
 *
 */
@Repository
public interface RepositorioCaja extends CrudRepository<Caja, Integer> {
	/**
	 * Busca una caja por su nombre
	 * @param nombreCaja
	 * @return
	 */
	public Caja findCajaByNombreCaja(String nombreCaja);
}

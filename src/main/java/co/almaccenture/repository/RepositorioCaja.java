package co.almaccenture.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.almaccenture.model.Caja;

@Repository
public interface RepositorioCaja extends CrudRepository<Caja, Integer> {

	public Caja findOne(Integer idCaja);
}

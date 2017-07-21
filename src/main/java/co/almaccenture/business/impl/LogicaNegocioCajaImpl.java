package co.almaccenture.business.impl;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.almaccenture.business.LogicaNegocioCaja;
import co.almaccenture.model.Caja;
import co.almaccenture.repository.RepositorioCaja;

@Service
public class LogicaNegocioCajaImpl implements LogicaNegocioCaja {
	
	@Autowired
	RepositorioCaja cajaRepo;

	@Override
	public Caja generarCajaAleatoria() {
		ArrayList<Caja> cajas = (ArrayList<Caja>) cajaRepo.findAll();
		Random random = new Random();
		int index = random.nextInt(cajas.size()-1);
		return cajas.get(index);
	}

}

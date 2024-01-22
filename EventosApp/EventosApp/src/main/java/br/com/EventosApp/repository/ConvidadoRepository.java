package br.com.EventosApp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.EventosApp.models.Convidado;
import br.com.EventosApp.models.Evento;
import io.micrometer.observation.Observation.Event;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
	Iterable<Convidado> findByEvento(Evento evento);
	Convidado findByRg(String rg);
}

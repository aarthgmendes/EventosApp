package br.com.EventosApp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.EventosApp.models.Evento;
import io.micrometer.observation.Observation.Event;

public interface EventoRepository extends CrudRepository<Evento, String>{
	Evento findById(long id);
	
}

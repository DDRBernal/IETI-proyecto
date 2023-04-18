package edu.eci.ieti.service.componente;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import edu.eci.ieti.repository.Componente;


public interface ComponenteService {

    Componente save(LinkedHashMap<String, String> componente);

    Optional<Componente> findById(String id);

    List<Componente> all();

    void deleteById(String id);

    Componente update(Componente componente, String componenteId);
}

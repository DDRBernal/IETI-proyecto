package edu.eci.ieti.service.componente;

import java.util.List;
import java.util.Optional;
import edu.eci.ieti.repository.Componente;
import org.springframework.stereotype.Service;


public interface ComponenteService {

    Componente save(Componente user);

    Optional<Componente> findById(String id);

    List<Componente> all();

    void deleteById(String id);

    Componente update(Componente componente, String componenteId);
}

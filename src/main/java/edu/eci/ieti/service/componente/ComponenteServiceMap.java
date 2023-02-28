package edu.eci.ieti.service.componente;

import edu.eci.ieti.repository.Componente;
import edu.eci.ieti.service.componente.persistence.ComponenteServicePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComponenteServiceMap implements ComponenteService {

    @Autowired
    private ComponenteServicePersistence componenteServicePersistence = new ComponenteServicePersistence();

    @Override
    public Componente save(Componente componente) {
        componenteServicePersistence.save(componente);
        return componente;
    }

    @Override
    public Optional<Componente> findById(String id) {
        return Optional.of(componenteServicePersistence.findById(id));
    }

    @Override
    public List<Componente> all() {
        return componenteServicePersistence.all();
    }

    @Override
    public void deleteById(String id) {
        componenteServicePersistence.deleteById(id);
    }

    @Override
    public Componente update(Componente componente, String componenteId) {
        componenteServicePersistence.update(componente,componenteId);
        return componente;
    }
}

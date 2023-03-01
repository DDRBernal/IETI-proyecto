package edu.eci.ieti.controller.componente;

import edu.eci.ieti.exception.UserNotFoundException;
import edu.eci.ieti.repository.*;
import edu.eci.ieti.repository.user.User;
import edu.eci.ieti.repository.user.UserDto;
import edu.eci.ieti.service.componente.ComponenteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/componente/")
public class ComponenteController {

    private final ComponenteService componenteService;

    public ComponenteController(@Autowired ComponenteService componenteService){
        this.componenteService = componenteService;
    }

    @PostMapping
    public ResponseEntity<Componente> createComponent(@RequestBody ComponentRequest<?> request) {
        URI createdUserUri = URI.create("");
        try {
            // Obtener la clase del objeto contenido en la solicitud
            Class<?> clazz = request.getComponent().getClass();
            // Verificar que la clase sea una subclase de Componente
            if (!Componente.class.isAssignableFrom(clazz)) {
                throw new IllegalArgumentException("Class not a subclass of Componente");
            }
            // Instanciar el objeto correcto según el tipo de clase que se envía en la solicitud
            Componente componente = (Componente) clazz.newInstance();
            BeanUtils.copyProperties(request.getComponent(), componente);
            componenteService.save(componente);
            return ResponseEntity.created(createdUserUri).body(componente);
        } catch (Exception e) {
            return ResponseEntity.created(createdUserUri).body(null);
        }
    }

    @GetMapping(value = "/getAllComponents")
    public ResponseEntity<List<Componente>> getAllComponent() {
        List<Componente> componentes= componenteService.all();
        return ResponseEntity.ok(componentes);
    }

    @GetMapping("{id}")
    public ResponseEntity<Componente> findById(@PathVariable("id") String id) {
        Optional<Componente> op = componenteService.findById(id);
        if (op.isEmpty()) throw new UserNotFoundException(id);
        return ResponseEntity.ok(op.get());
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<User> updateComponent(@PathVariable("id") String id, @RequestBody Componente componente) {
        Componente componente1 = new CPU(componente.getId());
        Optional<Componente> componentes = componenteService.findById(id);
        if (componentes.isPresent()){
            componenteService.update(componente1,id);
            componenteService.save(componentes.get());
            return ResponseEntity.ok(null);
        }else{
            throw new UserNotFoundException(id);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteComponent(@PathVariable("id") String id) {
        if(componenteService.findById(id).isEmpty()) throw new UserNotFoundException(id);
        componenteService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}

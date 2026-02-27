package com.hibernate.tareas.controller;

import com.hibernate.tareas.DTO.TareaCreateDTO;
import com.hibernate.tareas.DTO.TareaDTO;
import com.hibernate.tareas.service.TareaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    // POST /api/tareas  (crear tarea del usuario logueado)
    @PostMapping
    public ResponseEntity<TareaDTO> crear(@RequestBody TareaCreateDTO dto, Principal principal) {
        String username = principal.getName();
        TareaDTO creada = tareaService.crearTarea(dto, username);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    // GET /api/tareas/mias  (listar mis tareas)
    @GetMapping("/mias")
    public ResponseEntity<List<TareaDTO>> misTareas(Principal principal) {
        String username = principal.getName();
        List<TareaDTO> lista = tareaService.listarMisTareas(username);
        return ResponseEntity.ok(lista);
    }

    // PUT /api/tareas/{id}/completar (marcar como completada si es tuya)
    @PutMapping("/{id}/completar")
    public ResponseEntity<TareaDTO> completar(@PathVariable Long id, Principal principal) {
        String username = principal.getName();
        TareaDTO actualizada = tareaService.completarTarea(id, username);
        return ResponseEntity.ok(actualizada);
    }

    // DELETE /api/tareas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, Principal principal) {
        String username = principal.getName();
        tareaService.eliminarTarea(id, username);
        return ResponseEntity.noContent().build(); // 204
    }

    @PutMapping("/{id}/progreso")
    public ResponseEntity<TareaDTO> ponerEnProgreso(@PathVariable Long id, Principal principal) {
        String username = principal.getName();
        TareaDTO actualizada = tareaService.ponerEnProgreso(id, username);
        return ResponseEntity.ok(actualizada);
    }

    @PutMapping("/{id}/pendiente")
    public ResponseEntity<?> marcarPendiente(@PathVariable Long id) {
        tareaService.marcarPendiente(id);
        return ResponseEntity.ok().build();
    }
}
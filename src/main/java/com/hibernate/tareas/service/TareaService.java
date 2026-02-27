package com.hibernate.tareas.service;

import com.hibernate.tareas.DTO.TareaCreateDTO;
import com.hibernate.tareas.DTO.TareaDTO;
import com.hibernate.tareas.entity.EstadoTarea;
import com.hibernate.tareas.entity.Tarea;
import com.hibernate.tareas.entity.Usuario;
import com.hibernate.tareas.mapper.TareaMapper;
import com.hibernate.tareas.repository.TareaRepository;
import com.hibernate.tareas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;
    private final UsuarioRepository usuarioRepository;

    public TareaService(TareaRepository tareaRepository, UsuarioRepository usuarioRepository) {
        this.tareaRepository = tareaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public TareaDTO crearTarea(TareaCreateDTO dto, String username) {
        Usuario owner = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));

        Tarea tarea = TareaMapper.toEntity(dto);
        tarea.setOwner(owner);

        // por las dudas, si el mapper no lo setea
        if (tarea.getEstado() == null) {
            tarea.setEstado(EstadoTarea.PENDIENTE);
        }

        Tarea guardada = tareaRepository.save(tarea);
        return TareaMapper.toDTO(guardada);
    }

    public List<TareaDTO> listarMisTareas(String username) {
        return tareaRepository.findByOwner_Usuario(username)
                .stream()
                .map(TareaMapper::toDTO)
                .toList();
    }

    public TareaDTO completarTarea(Long tareaId, String username) {
        Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + tareaId));

        // Validación CLAVE: que la tarea sea del usuario logueado
        String ownerUsername = tarea.getOwner().getUsuario();
        if (!ownerUsername.equals(username)) {
            throw new RuntimeException("No tenés permiso para modificar esta tarea.");
        }

        tarea.setEstado(EstadoTarea.COMPLETADA);
        Tarea guardada = tareaRepository.save(tarea);

        return TareaMapper.toDTO(guardada);
    }

    public void eliminarTarea(Long tareaId, String username) {

        Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + tareaId));

        // Validación de dueño
        if (!tarea.getOwner().getUsuario().equals(username)) {
            throw new RuntimeException("No tenés permiso para eliminar esta tarea.");
        }

        tareaRepository.delete(tarea);
    }

    public TareaDTO ponerEnProgreso(Long tareaId, String username) {
        Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + tareaId));

        if (!tarea.getOwner().getUsuario().equals(username)) {
            throw new RuntimeException("No tenés permiso para modificar esta tarea.");
        }

        tarea.setEstado(EstadoTarea.EN_PROGRESO);
        Tarea guardada = tareaRepository.save(tarea);

        return TareaMapper.toDTO(guardada);
    }

    public void marcarPendiente(Long id) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrada"));

        tarea.setEstado(EstadoTarea.PENDIENTE);
        tareaRepository.save(tarea);
    }
}
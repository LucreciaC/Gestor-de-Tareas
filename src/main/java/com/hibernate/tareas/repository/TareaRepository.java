package com.hibernate.tareas.repository;

import com.hibernate.tareas.entity.EstadoTarea;
import com.hibernate.tareas.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    // Trae todas las tareas del usuario logueado (por username)
    List<Tarea> findByOwner_Usuario(String usuario);

    // Para filtrar por estado:
    List<Tarea> findByOwner_UsuarioAndEstado(String usuario, EstadoTarea estado);
}
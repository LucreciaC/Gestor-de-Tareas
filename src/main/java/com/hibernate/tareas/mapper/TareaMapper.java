package com.hibernate.tareas.mapper;

import com.hibernate.tareas.DTO.TareaCreateDTO;
import com.hibernate.tareas.DTO.TareaDTO;
import com.hibernate.tareas.entity.EstadoTarea;
import com.hibernate.tareas.entity.Tarea;

public class TareaMapper {

    private TareaMapper() {}

    // Entity -> DTO (para devolver al cliente)
    public static TareaDTO toDTO(Tarea entity) {
        if (entity == null) return null;

        return new TareaDTO(
                entity.getId(),
                entity.getTitulo(),
                entity.getDescripcion(),
                entity.getEstado() != null ? entity.getEstado().name() : null
        );
    }

    // DTO (POST) -> Entity (para crear)
    // El owner y el estado los seteamos en el Service.
    public static Tarea toEntity(TareaCreateDTO dto) {
        if (dto == null) return null;

        Tarea t = new Tarea();
        t.setTitulo(dto.getTitulo());
        t.setDescripcion(dto.getDescripcion());
        t.setEstado(EstadoTarea.PENDIENTE); // default
        return t;
    }

    // Para updates futuros (si después querés PUT de titulo/descripcion/estado)
    public static void updateEntityFromDTO(TareaDTO dto, Tarea entity) {
        if (dto == null || entity == null) return;

        if (dto.getTitulo() != null) entity.setTitulo(dto.getTitulo());
        if (dto.getDescripcion() != null) entity.setDescripcion(dto.getDescripcion());

        if (dto.getEstado() != null) {
            entity.setEstado(EstadoTarea.valueOf(dto.getEstado()));
        }
    }
}
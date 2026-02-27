package com.hibernate.tareas.service;

import com.hibernate.tareas.DTO.RegisterDTO;
import com.hibernate.tareas.entity.Usuario;
import com.hibernate.tareas.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterDTO dto) {
        if (dto.getUsuario() == null || dto.getUsuario().isBlank())
            throw new RuntimeException("El usuario no puede estar vacío");
        if (dto.getPassword() == null || dto.getPassword().isBlank())
            throw new RuntimeException("La password no puede estar vacía");

        if (usuarioRepository.findByUsuario(dto.getUsuario()).isPresent()) {
            throw new RuntimeException("Ya existe el usuario: " + dto.getUsuario());
        }

        Usuario u = new Usuario();
        u.setUsuario(dto.getUsuario());
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        u.setRol("USER"); // por defecto

        usuarioRepository.save(u);
    }
}
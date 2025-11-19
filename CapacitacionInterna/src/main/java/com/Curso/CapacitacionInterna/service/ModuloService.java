package com.Curso.CapacitacionInterna.service;

import com.Curso.CapacitacionInterna.dto.ModuloDTO;
import com.Curso.CapacitacionInterna.model.Modulo;
import com.Curso.CapacitacionInterna.repository.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    // Obtener módulos por curso
    public List<ModuloDTO> obtenerModulosPorCurso(Long cursoId) {
        return moduloRepository.findByCursoIdOrderByOrden(cursoId)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Eliminar módulo
    public void eliminarModulo(Long id) {
        if (!moduloRepository.existsById(id)) {
            throw new RuntimeException("Módulo no encontrado");
        }
        moduloRepository.deleteById(id);
    }

    // Método auxiliar para convertir entidad a DTO
    private ModuloDTO convertirADTO(Modulo modulo) {
        return new ModuloDTO(
                modulo.getId(),
                modulo.getTitulo(),
                modulo.getDescripcion(),
                modulo.getTipo(),
                modulo.getContenido(),
                modulo.getOrden()
        );
    }
}
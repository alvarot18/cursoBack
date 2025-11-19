package com.Curso.CapacitacionInterna.controller;

import com.Curso.CapacitacionInterna.dto.ModuloDTO;
import com.Curso.CapacitacionInterna.service.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/modulos")
@Tag(name = "Modulo", description = "Controlador para gestión de módulos de cursos")
public class ModuloController {

    @Autowired
    private ModuloService moduloService;

    @GetMapping("/curso/{cursoId}")
    @Operation(summary = "Obtener módulos por curso", description = "Retorna los módulos de un curso ordenados")
    public ResponseEntity<List<ModuloDTO>> obtenerModulosPorCurso(@PathVariable Long cursoId) {
        List<ModuloDTO> modulos = moduloService.obtenerModulosPorCurso(cursoId);
        return ResponseEntity.ok(modulos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar módulo", description = "Elimina un módulo permanentemente")
    public ResponseEntity<Void> eliminarModulo(@PathVariable Long id) {
        try {
            moduloService.eliminarModulo(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
package com.Curso.CapacitacionInterna.controller;

import com.Curso.CapacitacionInterna.dto.InscripcionCreateDTO;
import com.Curso.CapacitacionInterna.dto.InscripcionDTO;
import com.Curso.CapacitacionInterna.model.Inscripcion;
import com.Curso.CapacitacionInterna.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@Tag(name = "Inscripcion", description = "Controlador para gestión de inscripciones de usuarios a cursos")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @PostMapping
    @Operation(summary = "Inscribir usuario a curso", description = "Crea una nueva inscripción de usuario a curso")
    public ResponseEntity<InscripcionDTO> inscribirUsuario(@RequestBody InscripcionCreateDTO inscripcionCreateDTO) {
        try {
            InscripcionDTO inscripcionCreada = inscripcionService.inscribirUsuario(inscripcionCreateDTO);
            return ResponseEntity.ok(inscripcionCreada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todas las inscripciones", description = "Retorna todas las inscripciones del sistema")
    public ResponseEntity<List<InscripcionDTO>> obtenerTodasLasInscripciones() {
        List<InscripcionDTO> inscripciones = inscripcionService.obtenerTodasLasInscripciones();
        return ResponseEntity.ok(inscripciones);
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Obtener inscripciones por usuario", description = "Retorna todas las inscripciones de un usuario")
    public ResponseEntity<List<InscripcionDTO>> obtenerInscripcionesPorUsuario(@PathVariable Long usuarioId) {
        List<InscripcionDTO> inscripciones = inscripcionService.obtenerInscripcionesPorUsuario(usuarioId);
        return ResponseEntity.ok(inscripciones);
    }

    @GetMapping("/curso/{cursoId}")
    @Operation(summary = "Obtener inscripciones por curso", description = "Retorna todas las inscripciones de un curso")
    public ResponseEntity<List<InscripcionDTO>> obtenerInscripcionesPorCurso(@PathVariable Long cursoId) {
        List<InscripcionDTO> inscripciones = inscripcionService.obtenerInscripcionesPorCurso(cursoId);
        return ResponseEntity.ok(inscripciones);
    }

    @PutMapping("/progreso")
    @Operation(summary = "Actualizar progreso", description = "Actualiza el progreso de un usuario en un curso")
    public ResponseEntity<InscripcionDTO> actualizarProgreso(
            @RequestParam Long usuarioId,
            @RequestParam Long cursoId,
            @RequestParam Integer progreso) {
        try {
            InscripcionDTO inscripcionActualizada = inscripcionService.actualizarProgreso(usuarioId, cursoId, progreso);
            return ResponseEntity.ok(inscripcionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Obtener inscripciones por estado", description = "Retorna inscripciones filtradas por estado")
    public ResponseEntity<List<InscripcionDTO>> obtenerInscripcionesPorEstado(@PathVariable Inscripcion.Estado estado) {
        List<InscripcionDTO> inscripciones = inscripcionService.obtenerInscripcionesPorEstado(estado);
        return ResponseEntity.ok(inscripciones);
    }

    @DeleteMapping
    @Operation(summary = "Eliminar inscripción", description = "Elimina la inscripción de un usuario a un curso")
    public ResponseEntity<Void> eliminarInscripcion(
            @RequestParam Long usuarioId,
            @RequestParam Long cursoId) {
        try {
            inscripcionService.eliminarInscripcion(usuarioId, cursoId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
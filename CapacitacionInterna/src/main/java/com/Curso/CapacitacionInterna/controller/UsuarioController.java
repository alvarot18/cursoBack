package com.Curso.CapacitacionInterna.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuario", description = "Controlador para gestión de usuarios")
public class UsuarioController {

    @GetMapping("/hola")
    @Operation(summary = "Método de prueba", description = "Retorna un saludo de prueba")
    public String holaMundo() {
        return "¡Hola Mundo desde el UsuarioController!";
    }
}
package com.Curso.CapacitacionInterna.dto;

import com.Curso.CapacitacionInterna.model.Modulo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuloDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private Modulo.TipoContenido tipo;
    private String contenido;
    private Integer orden;
}
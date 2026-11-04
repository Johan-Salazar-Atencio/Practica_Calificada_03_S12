package com.tecsup.empresa.dto;

import com.tecsup.empresa.model.EstadoEmpresa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Datos de entrada validados; la entidad no se expone para no aceptar campos como el id.
public record EmpresaRequest(

        @NotBlank(message = "El RUC es obligatorio")
        @Pattern(regexp = "\\d{11}", message = "El RUC debe tener 11 dígitos")
        String ruc,

        @NotBlank(message = "La razón social es obligatoria")
        @Size(max = 150, message = "La razón social no puede pasar de 150 caracteres")
        String razonSocial,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no tiene un formato válido")
        String correo,

        // Opcional: si viene, debe ser numérico (una validación sin valor se considera correcta).
        @Pattern(regexp = "\\d{7,15}", message = "El teléfono debe tener entre 7 y 15 dígitos")
        String telefono,

        @NotNull(message = "El estado es obligatorio (ACTIVA o INACTIVA)")
        EstadoEmpresa estado
) {
}

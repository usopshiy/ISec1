package usopshiy.isec1.dto;

import jakarta.validation.constraints.NotBlank;

public record ProductDTO(
        @NotBlank
        String name
) {}

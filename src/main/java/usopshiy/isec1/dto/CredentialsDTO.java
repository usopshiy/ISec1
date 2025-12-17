package usopshiy.isec1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CredentialsDTO(
        @NotBlank
        @Size(min = 5, max = 50)
        String login,
        @NotBlank
        @Size(min = 5, max = 50)
        String password
) {}

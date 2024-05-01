package com.fleming99.MarketplaceOnline.core.validation;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WebUser {

    @NotNull(message = "is required")
    @Size(min = 2, message = "requires minimum 2 letters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "requires only letters")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 2, message = "requires minimum 2 letters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "requires only letters")
    private String lastName;

    @NotNull(message = "is required")
    @Pattern(regexp= "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "invalid format (example@email.com)")
    private String email;

    @NotNull(message = "is required")
    @Size(min = 8, message = "requires minimum 8 characters")
    private String password;

    @NotNull(message = "is required")
    @Size(min = 14, max = 14, message = "invalid length")
    @Pattern(regexp = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}", message = "invalid format (xxx.xxx.xxx-xx)")
    private String cpf;
}

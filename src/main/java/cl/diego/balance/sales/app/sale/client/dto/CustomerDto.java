package cl.diego.balance.sales.app.sale.client.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CustomerDto {

    private Long   id;
    @NotBlank
    private String rut;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String names;
    @NotBlank
    private String lastname1;
    private String lastname2;
    @NotBlank
    private String cellphone;
}

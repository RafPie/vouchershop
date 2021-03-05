package pl.rpietrzak.vouchershop.crm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @NotNull
    private String street;
    @NotNull
    private String zip;
    @NotNull
    private String city;
}

package pl.rpietrzak.vouchershop.crm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String firstname;
    @NotNull
    private String lastname;

    @Embedded
    @NotNull
    @Valid
    private Address address;
}

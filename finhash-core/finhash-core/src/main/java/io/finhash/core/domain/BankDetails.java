package io.finhash.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankDetails {

    String code;
    String name;
    Double amount = 0.0;
}

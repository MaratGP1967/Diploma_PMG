package ru.netology.diploma.data.constructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditConstructorData {
    private String id;
    private String bank_id;
    private String created;
    private String status;
}

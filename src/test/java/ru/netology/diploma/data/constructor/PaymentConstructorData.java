package ru.netology.diploma.data.constructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentConstructorData {
    private String id;
    private String amount;
    private String created;
    private String status;
    private String transaction_id;
}

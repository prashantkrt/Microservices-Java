package com.mylearning.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsDto {
    @NotEmpty(message = "mobile Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "mobile number must be 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "cardNumber cannot be null or empty")
    @Pattern(regexp = "^$|[0-9]{12}", message = "card number must be 12 digits of length")
    private String cardNumber;

    @NotEmpty(message = "CardType can not be a null or empty")
    private String cardType;

    @Positive( message = "Total card limit should be greater than zero")
    private Long totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    private Long amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    private Long availableAmount;
}
package com.mylearning.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name="Account",
        description = "Holds the account details of the customer"
)
public class AccountDto {

    @Schema(
            name="Account Number",
            description = "holds the account number of the customer's account"
    )
    @NotEmpty(message = "Account number cannot be null or empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(
            name="Account Type",
            description = "holds the account type of the customer's account"
    )
    @NotEmpty(message = "AccountType cannot be null or empty")
    private String accountType;

    @Schema(
            name="Account Branch Address",
            description = "holds the account branch address of the customer's account"
    )
    @NotEmpty(message = "Branch Address cannot be null or empty")
    private String branchAddress;
}

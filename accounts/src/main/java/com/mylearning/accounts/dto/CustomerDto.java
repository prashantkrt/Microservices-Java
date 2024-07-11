package com.mylearning.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name="Customer",
        description = "Schema to hold the customer information"
)
public class CustomerDto {

    @Schema(
            description = "Hold the name of the customer",
            example = "Rajendra Mishra"
    )
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5, max = 20, message = "Name length should be of min 5 length to 20 length")
    private String name;

    @Schema(
            description = "Hold the email of the customer",
            example = "abcd@gmail.com"
    )
    @NotEmpty(message = "email address cannot be empty")
    @Email(message = "email address should be valid value")
    private String email;

    @Schema(
            description = "Hold the mobile number of the customer",
            example = "911123123xx"
    )
    @Pattern(regexp = "^$|[0-9]{10}", message = "mobile number must be 10 digits")
    private String mobileNumber;

    private AccountDto accountDto;
}

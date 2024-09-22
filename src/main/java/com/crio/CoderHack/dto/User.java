package com.crio.CoderHack.dto;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    @NotBlank(message = "User Id can not be blank")
    private String userId;

    @NotBlank(message = "User Name can not be blank")
    private String username;

    @Min(value = 0, message = "The score must be greater than or equal to 0")
    @Max(value = 100, message = "The score must be less than or equal to 100")
    private int score;

    private List<String> badges;

}

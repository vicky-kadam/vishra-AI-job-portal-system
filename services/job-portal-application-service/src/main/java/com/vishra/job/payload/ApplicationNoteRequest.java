package com.vishra.job.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApplicationNoteRequest {


    @NotBlank(message = "Content cannot be blank")
    @Size(max = 200,message = "Content cannot be more than 200 characters")
    private String content;


}


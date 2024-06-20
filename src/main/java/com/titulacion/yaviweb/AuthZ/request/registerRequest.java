package com.titulacion.yaviweb.AuthZ.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class registerRequest {
    String username;
    String lastName;
    Integer number;
    String email;
    String password;
}

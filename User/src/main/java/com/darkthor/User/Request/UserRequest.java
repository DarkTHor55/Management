package com.darkthor.User.Request;

import jakarta.validation.constraints.Email;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    @NonNull
    private String name;
    @Email
    private String email;
    @NonNull
    private String password;
    private String role;
}

package com.darkthor.Request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    @NonNull
    private String name;
    private String email;
    @NonNull
    private String password;
    private String role;
}

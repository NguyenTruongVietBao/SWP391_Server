package com.math.mathcha.dto.request;

import com.math.mathcha.entity.Role;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Role}
 */
@Value
public class RoleDTO implements Serializable {
    int role_id;
    String role_name;
}
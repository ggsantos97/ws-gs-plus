package com.client.ws.gsplus.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Table(name = "user_type")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserType implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_type_id")
    private Long id;

    private String name;
    private String description;

    @Override
    public String getAuthority() {
        return name;
    }
}

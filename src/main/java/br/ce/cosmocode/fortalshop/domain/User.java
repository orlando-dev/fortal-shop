package br.ce.cosmocode.fortalshop.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "users", schema = "marketplace")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    @NotBlank(message = "O nome de usuário não pode estar vazio")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "nome não pode estar vazio")
    private String name;
    
    @Column(nullable = false)
    @NotBlank(message = "A senha não pode estar vazia")
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
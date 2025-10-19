package com.desktop_spring.desktop.repository;

import com.desktop_spring.desktop.domain.Cliente;
import com.desktop_spring.desktop.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContaRepository extends JpaRepository<Conta, UUID> {
}

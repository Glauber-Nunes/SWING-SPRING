package com.desktop_spring.desktop.repository;

import com.desktop_spring.desktop.domain.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepositoRepository extends JpaRepository<Deposito, UUID> {
}

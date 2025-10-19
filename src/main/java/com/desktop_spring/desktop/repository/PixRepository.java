package com.desktop_spring.desktop.repository;

import com.desktop_spring.desktop.domain.Pix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PixRepository extends JpaRepository<Pix, UUID> {
}

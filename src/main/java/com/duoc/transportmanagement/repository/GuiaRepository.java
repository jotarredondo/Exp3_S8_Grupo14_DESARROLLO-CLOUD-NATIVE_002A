package com.duoc.transportmanagement.repository;

import com.duoc.transportmanagement.model.GuiaDespacho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuiaRepository extends JpaRepository<GuiaDespacho, Long> {
}

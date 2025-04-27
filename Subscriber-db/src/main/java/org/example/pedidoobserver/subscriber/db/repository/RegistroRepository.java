package org.example.pedidoobserver.subscriber.db.repository;

import org.example.pedidoobserver.subscriber.db.entity.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro, Integer> {
}
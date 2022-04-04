package com.sprint3.prova.repository;

import com.sprint3.prova.modelo.Estado;
import com.sprint3.prova.modelo.Regiao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    List<Estado> findByRegiao(Regiao regiao);

}

package com.subestacoes.repository;

import com.subestacoes.model.SubestacaoModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SubestacaoRepository extends JpaRepository<SubestacaoModel, Long> {
    SubestacaoModel findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);
}

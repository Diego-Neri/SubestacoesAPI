package com.subestacoes.repository;

import com.subestacoes.model.RedeMTModel;
import com.subestacoes.model.SubestacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RedeMTRepository extends  JpaRepository <RedeMTModel, String>{
    RedeMTModel findByCodigo(String codigo);

    List<RedeMTModel> findBySubestacao(SubestacaoModel subestacao);
}

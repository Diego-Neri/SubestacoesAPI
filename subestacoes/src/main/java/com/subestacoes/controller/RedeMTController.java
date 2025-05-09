package com.subestacoes.controller;

import com.subestacoes.model.RedeMTModel;
import com.subestacoes.repository.RedeMTRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Rede")
public class RedeMTController {

    @Autowired
    private RedeMTRepository redeMTRepository;

    // Retorna todas as redes MT
    @GetMapping
    public ResponseEntity<?> getAllRedes() {
        try {
            List<RedeMTModel> redes = redeMTRepository.findAll();
            return ResponseEntity.ok(redes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar redes MT: " + e.getMessage());
        }
    }

    // Retorna uma rede MT específica pelo ID
    @GetMapping("/{codigo}")
    public ResponseEntity<?> PegarRedeCodigo(@PathVariable String codigo) {
        try {
            RedeMTModel rede = redeMTRepository.findByCodigo(codigo);
            if (rede == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Rede MT com o código '" + codigo + "' não encontrada.");
            }
            return ResponseEntity.ok(rede);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar rede MT: " + e.getMessage());
        }
    }

    // Cria novas redes MT
    @PostMapping
    public ResponseEntity<?> CriarRede(@RequestBody Object input) {
        try {
            if (input instanceof List<?>) {
                List<RedeMTModel> redes = (List<RedeMTModel>) input;
                redeMTRepository.saveAll(redes);
                return ResponseEntity.status(HttpStatus.CREATED).body("Redes salvas com sucesso.");
            } else if (input instanceof Map<?, ?>) {
                RedeMTModel rede = new ObjectMapper().convertValue(input, RedeMTModel.class);
                redeMTRepository.save(rede);
                return ResponseEntity.status(HttpStatus.CREATED).body("Rede salva com sucesso.");
            } else {
                return ResponseEntity.badRequest().body("Formato de entrada inválido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar redes MT: " + e.getMessage());
        }
    }

    // Atualiza rede MT
    @PutMapping("/{codigo}")
    public ResponseEntity<?> AtualizarRedeMT(@PathVariable String codigo, @RequestBody RedeMTModel redeMTModel) {
        try {
            RedeMTModel redeExistente = redeMTRepository.findByCodigo(codigo);
            if (redeExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Rede MT com o código '" + codigo + "' não encontrada.");
            }

            redeExistente.setNome(redeMTModel.getNome());
            redeExistente.setTensaoNominal(redeMTModel.getTensaoNominal());
            redeMTRepository.save(redeExistente);

            return ResponseEntity.ok("Rede MT atualizada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar rede MT: " + e.getMessage());
        }
    }

    // Exclui uma rede MT pelo código
    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> deleteRede(@PathVariable String codigo) {
        try {
            RedeMTModel rede = redeMTRepository.findByCodigo(codigo);
            if (rede == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Rede MT com o código '" + codigo + "' não encontrada.");
            }

            redeMTRepository.delete(rede);
            return ResponseEntity.ok("Rede MT excluída com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao excluir rede MT: " + e.getMessage());
        }
    }
}

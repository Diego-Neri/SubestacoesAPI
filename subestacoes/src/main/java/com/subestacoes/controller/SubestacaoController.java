package com.subestacoes.controller;
import com.subestacoes.model.RedeMTModel;
import com.subestacoes.model.SubestacaoModel;
import com.subestacoes.repository.RedeMTRepository;
import com.subestacoes.repository.SubestacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RestController
@RequestMapping("/Subestacao")
public class SubestacaoController {
    @Autowired
    private SubestacaoRepository subestacaoRepository;

    @Autowired
    private RedeMTRepository redeMTRepository;

    // Retorna todas as subestações
    @GetMapping
    public List<SubestacaoModel> PegarTodasSubestacoes() {
        return (List<SubestacaoModel>) subestacaoRepository.findAll();
    }


    // Retorna uma subestação específica pelo codigo
    @GetMapping("/{codigo}")
    public SubestacaoModel PegarSubestacaoCodigo(@PathVariable String codigo) {
        return subestacaoRepository.findByCodigo(codigo);
    }

    // Cria uma nova subestação
    @PostMapping
    public SubestacaoModel CriarSubestacao(@RequestBody SubestacaoModel subestacaoModel) {
        if (subestacaoRepository.existsByCodigo(subestacaoModel.getCodigo())) {
            throw new RuntimeException("Já existe uma subestação com este código.");
        }
        return subestacaoRepository.save(subestacaoModel);
    }

    // Retorna as redes MT associadas a uma subestação
    @GetMapping("/{codigo}/Rede")
    public List<RedeMTModel> PegarRedesPorSubestacao(@PathVariable String codigo) {
        SubestacaoModel subestacao = subestacaoRepository.findByCodigo(codigo);
        if (subestacao == null) {
            throw new RuntimeException("Subestação com código " + codigo + " não encontrada.");
        }
        List<RedeMTModel> redes = redeMTRepository.findBySubestacao(subestacao);
        if (redes.isEmpty()) {
            System.out.println("Nenhuma rede MT encontrada para a subestação: " + codigo);
        }
        return redes;
    }



    // Atualiza uma subestação existente
    @PutMapping("/{codigo}")
    public SubestacaoModel AtualizarSubestacao(@PathVariable String codigo, @RequestBody SubestacaoModel subestacaoModel) {
        SubestacaoModel subestacaoExistente = subestacaoRepository.findByCodigo(codigo);
        if (subestacaoExistente == null) {
            throw new RuntimeException("Subestação não encontrada.");
        }
        subestacaoExistente.setNome(subestacaoModel.getNome());
        subestacaoExistente.setLatitude(subestacaoModel.getLatitude());
        subestacaoExistente.setLongitude(subestacaoModel.getLongitude());
        return subestacaoRepository.save(subestacaoExistente);
    }


    // Deleta uma subestação
    @DeleteMapping("/{id}")
    public void DeletaSubestacao(@PathVariable Long id) {
        subestacaoRepository.deleteById(id);
    }
}

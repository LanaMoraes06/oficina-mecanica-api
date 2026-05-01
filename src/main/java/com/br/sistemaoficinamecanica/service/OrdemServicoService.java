package com.br.sistemaoficinamecanica.service;

import com.br.sistemaoficinamecanica.dto.ordemservico.OrdemServicoDTO;
import com.br.sistemaoficinamecanica.enums.StatusOS;
import com.br.sistemaoficinamecanica.model.entity.ItemPeca;
import com.br.sistemaoficinamecanica.model.entity.OrdemServico;
import com.br.sistemaoficinamecanica.model.entity.Peca;
import com.br.sistemaoficinamecanica.model.entity.Veiculo;
import com.br.sistemaoficinamecanica.repository.ItemPecaRepository;
import com.br.sistemaoficinamecanica.repository.OrdemServicoRepository;
import com.br.sistemaoficinamecanica.repository.PecaRepository;
import com.br.sistemaoficinamecanica.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ItemPecaRepository itemPecaRepository;

    @Autowired
    private PecaRepository pecaRepository;

    public OrdemServico create(OrdemServicoDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculo())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado."));

        OrdemServico os = new OrdemServico();
        os.setVeiculo(veiculo);

        os.setDataAbertura(LocalDateTime.now());
        os.setValorTotal(BigDecimal.ZERO);
        os.setStatus(StatusOS.ABERTA);

        return ordemServicoRepository.save(os);
    }

    public List<OrdemServico> getAll() {
        return ordemServicoRepository.findAll();
    }

    public OrdemServico getById(UUID id) {
        return ordemServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com id " + id));
    }

    public OrdemServico finalizar(UUID id) {
        OrdemServico os = getById(id);

        if (os.getStatus() == StatusOS.CONCLUIDA || os.getStatus() == StatusOS.CANCELADA) {
            throw new RuntimeException("Apenas OS ABERTA ou em ANDAMENTO podem ser concluídas.");
        }
        List<ItemPeca> itensUsados = itemPecaRepository.findByOrdemServicoId(os.getId());

        BigDecimal valorTotalCalculado = itensUsados.stream()
                .map(item -> item.getPeca().getPreco().multiply(BigDecimal.valueOf(item.getQuantidadeUtilizada())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        os.setValorTotal(valorTotalCalculado);
        os.setStatus(StatusOS.CONCLUIDA);

        return ordemServicoRepository.save(os);
    }

    public OrdemServico cancelar(UUID id) {
        OrdemServico os = getById(id);

        if (os.getStatus() == StatusOS.CONCLUIDA) {
            throw new RuntimeException("Não é possível cancelar uma OS que já foi concluída e paga.");
        }

        List<ItemPeca> itensUsados = itemPecaRepository.findByOrdemServicoId(os.getId());
        List<Peca> pecasAtualizadas = itensUsados.stream()
                .map(item -> {
                    Peca peca = item.getPeca();
                    peca.setQtdEstoque(peca.getQtdEstoque() + item.getQuantidadeUtilizada());
                    return peca;
                })
                .toList();
        pecaRepository.saveAll(pecasAtualizadas);

        os.setStatus(StatusOS.CANCELADA);
        return ordemServicoRepository.save(os);
    }
}
package com.br.sistemaoficinamecanica.service;

import com.br.sistemaoficinamecanica.dto.itemPeca.ItemPecaDTO;
import com.br.sistemaoficinamecanica.enums.StatusOS;
import com.br.sistemaoficinamecanica.model.entity.ItemPeca;
import com.br.sistemaoficinamecanica.model.entity.ItemPecaId;
import com.br.sistemaoficinamecanica.model.entity.OrdemServico;
import com.br.sistemaoficinamecanica.model.entity.Peca;
import com.br.sistemaoficinamecanica.repository.ItemPecaRepository;
import com.br.sistemaoficinamecanica.repository.OrdemServicoRepository;
import com.br.sistemaoficinamecanica.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ItemPecaService {

    @Autowired
    private ItemPecaRepository itemPecaRepository;

    @Autowired
    private PecaRepository pecaRepository;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public ItemPeca getById(UUID ordemServicoId, UUID pecaId) {
        return itemPecaRepository.findById(new ItemPecaId(ordemServicoId, pecaId))
                .orElseThrow(() -> new RuntimeException("Item não encontrado nesta Ordem de serviço."));
    }

    public ItemPeca create(ItemPecaDTO dto) {
        OrdemServico os = ordemServicoRepository.findById(dto.getOrdemServicoId())
                .orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada."));

        if (os.getStatus() == StatusOS.CONCLUIDA || os.getStatus() == StatusOS.CANCELADA) {
            throw new RuntimeException("Não é possível adicionar peças em uma OS fechada.");
        }

        return pecaRepository.findById(dto.getPecaId()).map(peca -> {
            if (peca.getQtdEstoque() < dto.getQuantidadeUtilizada()) {
                throw new RuntimeException("Estoque insuficiente.");
            }

            peca.setQtdEstoque(peca.getQtdEstoque() - dto.getQuantidadeUtilizada());
            pecaRepository.save(peca);

            ItemPeca novoItem = new ItemPeca(
                    new ItemPecaId(os.getId(), peca.getId()),
                    os,
                    peca,
                    dto.getQuantidadeUtilizada()
            );
            return itemPecaRepository.save(novoItem);

        }).orElseThrow(() -> new RuntimeException("Peça não encontrada."));
    }

    public ItemPeca update(UUID ordemServicoId, UUID pecaId, ItemPecaDTO dto) {
        return itemPecaRepository.findById(new ItemPecaId(ordemServicoId, pecaId)).map(item -> {


            if (item.getOrdemServico().getStatus() == StatusOS.CONCLUIDA ||
                    item.getOrdemServico().getStatus() == StatusOS.CANCELADA) {
                throw new RuntimeException("Não é possível alterar peças de uma ordem de serviço fechada.");
            }
            int diferenca = dto.getQuantidadeUtilizada() - item.getQuantidadeUtilizada();
            Peca peca = item.getPeca();

            if (diferenca > 0 && peca.getQtdEstoque() < diferenca) {
                throw new RuntimeException("Estoque insuficiente! Faltam " + diferenca + " unidades.");
            }
            peca.setQtdEstoque(peca.getQtdEstoque() - diferenca);
            pecaRepository.save(peca);
            item.setQuantidadeUtilizada(dto.getQuantidadeUtilizada());
            return itemPecaRepository.save(item);

        }).orElseThrow(() -> new RuntimeException("Item não encontrado."));
    }

    public void delete(UUID ordemServicoId, UUID pecaId) {
        itemPecaRepository.findById(new ItemPecaId(ordemServicoId, pecaId)).ifPresentOrElse(item -> {

            if (item.getOrdemServico().getStatus() == StatusOS.CONCLUIDA ||
                    item.getOrdemServico().getStatus() == StatusOS.CANCELADA) {
                throw new RuntimeException("Não é possível remover peças de uma ordem de serviço fechada.");
            }

            Peca peca = item.getPeca();
            peca.setQtdEstoque(peca.getQtdEstoque() + item.getQuantidadeUtilizada());
            pecaRepository.save(peca);

            itemPecaRepository.delete(item);

        }, () -> {
            throw new RuntimeException("Item não encontrado.");
        });
    }
}
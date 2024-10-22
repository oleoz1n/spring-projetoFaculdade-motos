package br.com.fiap.motos.service;

import br.com.fiap.motos.dto.request.CaracteristicaRequest;
import br.com.fiap.motos.dto.response.CaracteristicaResponse;
import br.com.fiap.motos.entity.Caracteristica;
import br.com.fiap.motos.repository.CaracteristicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CaracteristicaService implements ServiceDTO<Caracteristica, CaracteristicaRequest, CaracteristicaResponse>{

    @Autowired
    private CaracteristicaRepository repo;


    @Autowired
    private VeiculoService veiculoService;

    @Override
    public Collection<Caracteristica> findAll(Example<Caracteristica> example) {
        return repo.findAll(example);
    }

    @Override
    public Caracteristica findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Caracteristica save(Caracteristica e) {
        System.out.println(e);
        return repo.save(e);
    }

    @Override
    public Caracteristica toEntity(CaracteristicaRequest dto) {
        var veiculo = veiculoService.findById(dto.veiculo().id());

        return Caracteristica.builder()
                .nome(dto.nome())
                .veiculo(veiculo)
                .descricao(dto.descricao())
                .build();
    }

    @Override
    public CaracteristicaResponse toResponse(Caracteristica e) {
        var veiculo = veiculoService.toResponse(e.getVeiculo());
        return CaracteristicaResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .veiculo(veiculo)
                .descricao(e.getDescricao())
                .build();
    }
}

package br.com.jfernando.mudi.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jfernando.mudi.dto.OfertaDto;
import br.com.jfernando.mudi.model.Oferta;
import br.com.jfernando.mudi.model.Pedido;
import br.com.jfernando.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PostMapping
	public Oferta criaOferta(@RequestBody @Valid OfertaDto ofertaDto) {
		
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(ofertaDto.getPedidoId());
		if(!pedidoBuscado.isPresent()) {
			return null;
		}
		
		Pedido pedido = pedidoBuscado.get();
		Oferta nova = ofertaDto.toOferta();
		nova.setPedido(pedido);
		pedido.getOfertas().add(nova);
		pedidoRepository.save(pedido);
		
		return nova;
		
	}
	
}
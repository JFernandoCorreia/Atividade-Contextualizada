package br.com.jfernando.mudi.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jfernando.mudi.model.Pedido;
import br.com.jfernando.mudi.model.StatusPedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Cacheable("listaPedidos")
	List<Pedido> findByStatus(StatusPedido status, Pageable page);

	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username")
	List<Pedido> findByUser(@Param("username")String username);

	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username and p.status = :status")
	List<Pedido> findByUserAndStatus(@Param("status") StatusPedido status, @Param("username") String username);

}
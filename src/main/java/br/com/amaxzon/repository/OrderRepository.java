package br.com.amaxzon.repository;

import br.com.amaxzon.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}

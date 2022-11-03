package org.springframework.samples.petclinic.product;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<Product,Integer>{
    List<Product> findAll();
    @Query("SELECT u FROM ProductType u")
    List<ProductType> findAllProductTypes() throws DataAccessException;
    Optional<Product> findById(int id);
    Product findByName(String name);
    @Query("SELECT u FROM ProductType u WHERE u.name= :name")
    ProductType findProductTypeByName(@Param("name")String name);
    Product save(Product p);
    List<Product> findAllByPriceLessThan(Double price);                        
}

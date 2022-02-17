package utez.edu.mx.manejoErrores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import utez.edu.mx.manejoErrores.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{
    
}

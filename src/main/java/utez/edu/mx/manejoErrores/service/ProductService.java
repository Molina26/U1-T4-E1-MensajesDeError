package utez.edu.mx.manejoErrores.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import utez.edu.mx.manejoErrores.entity.Product;
import utez.edu.mx.manejoErrores.errors.ProductNotExistException;
import utez.edu.mx.manejoErrores.repository.IProductRepository;

@Service
public class ProductService {

    @Autowired
    IProductRepository iProductRepository;

    @Transactional(readOnly = true)
    public Product findProductById(long id) {

        try {

            return iProductRepository.findById(id).get();

        } catch (NoSuchElementException e) {
            throw new ProductNotExistException(String.valueOf(id));
        }
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProduct() {
        try {
            return iProductRepository.findAll();

        } catch (Exception e) {
            return null;
        }
    }

    public boolean saveProduct(Product product) {

        try {
            Product productObj = iProductRepository.save(product);

            if (productObj != null) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

    }
}

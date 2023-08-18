package br.com.quarkus.service;

import java.util.List;
import java.util.Optional;

import br.com.quarkus.dto.ProductDTO;
import br.com.quarkus.entity.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class ProductService {
    

    public List<Product> listProduct(){
        return Product.listAll();
    }

    @Transactional
    public Product saveProduct(ProductDTO dto){

        Product product = new Product();

        product.setName(dto.getName());
        product.setStock(dto.getStock());
        product.setPrice(dto.getPrice());

        product.persist();

        return product;
    }

    @Transactional
    public void updateProduct(Long id, ProductDTO dto){
        
        Product product = new Product();

        Optional<Product> productOp = Product.findByIdOptional(id);
        
        if(productOp.isEmpty()){
            throw new NullPointerException("Product not Found!");
        }

        product = productOp.get();

        product.setName(dto.getName());
        product.setStock(dto.getStock());
        product.setPrice(dto.getPrice());

        product.persist();
    }

    @Transactional
    public void removeProduct(Long id){

        Optional<Product> productOp = Product.findByIdOptional(id);

        if(productOp.isEmpty()){
            throw new NullPointerException("Product not Found!");
        }

        Product product = productOp.get();

        product.delete();
    }
}

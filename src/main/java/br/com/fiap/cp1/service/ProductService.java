package br.com.fiap.cp1.service;

import br.com.fiap.cp1.entity.ProdutosEntity;
import br.com.fiap.cp1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProdutosEntity> getAll() {
        return productRepository.findAll();
    }

    public Optional<ProdutosEntity> getById(Long idProduct) {
        return productRepository.findById(idProduct);
    }

    public ProdutosEntity update(Long idProduct, ProdutosEntity updatedProductData) {
        ProdutosEntity existingProduct = productRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException("Produto com ID " + idProduct + " não encontrado."));

        if (updatedProductData.getName() != null) {
            existingProduct.setName(updatedProductData.getName());
        }
        if (updatedProductData.getType() != null) {
            existingProduct.setType(updatedProductData.getType());
        }
        if (updatedProductData.getSector() != null) {
            existingProduct.setSector(updatedProductData.getSector());
        }
        if (updatedProductData.getSize() != 0.0) {
            existingProduct.setSize(updatedProductData.getSize());
        }
        if (updatedProductData.getUnitPrice() != 0.0) {
            existingProduct.setUnitPrice(updatedProductData.getUnitPrice());
        }

        return productRepository.save(existingProduct);
    }

    public ProdutosEntity save(ProdutosEntity products) {
        return  productRepository.save(products);
    }

    public void delete(Long idProduct) {
        productRepository.deleteById(idProduct);
    }
}

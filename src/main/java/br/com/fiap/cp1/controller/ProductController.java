package br.com.fiap.cp1.controller;

import br.com.fiap.cp1.dto.ProductRequest;
import br.com.fiap.cp1.dto.ProductResponse;
import br.com.fiap.cp1.entity.ProdutosEntity;
import br.com.fiap.cp1.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController implements ProductApi {

    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<List<ProductResponse>> getAll() {
        List<ProdutosEntity> products = productService.getAll();

        List<ProductResponse> response = products.stream()
                .map(ProductResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ProductResponse> getById(@PathVariable Long idProduct) {
       return productService.getById(idProduct)
               .map(ProductResponse::from)
               .map(ResponseEntity::ok)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado"));
    }

    @Override
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {

        ProdutosEntity product = ProdutosEntity.builder()
                .name(request.getName())
                .type(request.getType())
                .sector(request.getSector())
                .size(request.getSize())
                .unitPrice(request.getUnitPrice())
                .build();

        ProdutosEntity savedProduct = productService.save(product);

        ProductResponse response = ProductResponse.from(savedProduct);

        return ResponseEntity.ok(response);

    }

    @Override
    public ResponseEntity<ProductResponse> update(@PathVariable Long idProduct, @Valid @RequestBody ProductRequest request) {
        try {
            ProdutosEntity updatedProduct = ProdutosEntity.builder()
                    .name(request.getName())
                    .type(request.getType())
                    .sector(request.getSector())
                    .size(request.getSize())
                    .unitPrice(request.getUnitPrice())
                    .build();

            ProdutosEntity savedProduct = productService.update(idProduct, updatedProduct);

            ProductResponse response = ProductResponse.from(savedProduct);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long idProduct) {
        if (productService.getById(idProduct).isPresent()) {
            productService.delete(idProduct);

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

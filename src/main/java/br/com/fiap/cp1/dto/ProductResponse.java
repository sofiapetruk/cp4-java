package br.com.fiap.cp1.dto;

import br.com.fiap.cp1.entity.ProdutosEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Long idProduto;
    private String name;
    private String type;
    private String sector;
    private double size;
    private double unitPrice;

    public static ProductResponse from(ProdutosEntity produtosEntity) {
        return ProductResponse.builder()
                .idProduto(produtosEntity.getIdProduto())
                .name(produtosEntity.getName())
                .type(produtosEntity.getType())
                .sector(produtosEntity.getSector())
                .size(produtosEntity.getSize())
                .unitPrice(produtosEntity.getUnitPrice())
                .build();
    }

}

package br.com.fiap.cp1.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_CP1_PRODUTOS")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProdutosEntity {

    @Id
    @Column(name = "ID_PRODUTO", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto")
    @SequenceGenerator(name = "produto", sequenceName = "SQ_T_CP1_PRODUTOS", allocationSize = 1)
    private  Long idProduto;

    @Column(name = "NAME_PRODUCT", length = 100, nullable = false)
    private String name;

    @Column(name = "TYPE_PRODUCT", length = 100, nullable = false)
    private String type;

    @Column(name = "SECTOR", length = 200, nullable = false)
    private String sector;

    @Column(name = "SIZE_PRODUCT", nullable = false)
    private double size;

    @Column(name = "UNIT_PRICE", nullable = false)
    private double unitPrice;


}

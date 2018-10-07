package hu.unideb.inf.notebookservice.persistence.entity;

import lombok.*;

import javax.persistence.*;

import java.util.List;

import static hu.unideb.inf.notebookservice.commons.pojo.table.ColumnName.ProductColumnName.*;
import static hu.unideb.inf.notebookservice.commons.pojo.table.TableName.TABLE_NAME_PRODUCT;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME_PRODUCT)
public class ProductEntity extends BaseEntity<Long> {

    @Column(name = COLUMN_NAME_DESCRIPTION)
    private String description;

    @Column(name = COLUMN_NAME_TYPE)
    private String type;

    @ManyToOne
    private BrandEntity brand;

    @OneToMany
    private List<ServiceEntity> services;

    @ManyToOne
    private ClientEntity client;

    @Builder
    public ProductEntity(Long id, String description, String type, BrandEntity brand, ClientEntity client) {
        super(id);
        this.description = description;
        this.type = type;
        this.brand = brand;
        this.client = client;
    }
}

package hu.unideb.inf.notebookservice.persistence.entity;

import lombok.*;

import javax.persistence.*;

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

    @OneToOne
    private ServiceEntity service;

    @ManyToOne
    private ClientEntity client;

    @Builder
    public ProductEntity(Long id, String description, String type, BrandEntity brand, ServiceEntity service, ClientEntity client) {
        super(id);
        this.description = description;
        this.type = type;
        this.brand = brand;
        this.service = service;
        this.client = client;
    }
}

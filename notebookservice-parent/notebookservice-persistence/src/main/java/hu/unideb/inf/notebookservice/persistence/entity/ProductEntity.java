package hu.unideb.inf.notebookservice.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

import static hu.unideb.inf.notebookservice.commons.table.ColumnName.ProductColumnName.*;
import static hu.unideb.inf.notebookservice.commons.table.TableName.TABLE_NAME_PRODUCT;

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
    private List<MaintenanceEntity> services;

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

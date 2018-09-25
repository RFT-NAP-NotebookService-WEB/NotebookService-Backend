package hu.unideb.inf.notebookservice.persistence.entity;

import lombok.*;

import javax.persistence.*;

import java.util.List;

import static hu.unideb.inf.notebookservice.commons.pojo.table.ColumnName.BrandColumName.COLUMN_NAME_NAME;
import static hu.unideb.inf.notebookservice.commons.pojo.table.TableName.TABLE_NAME_BRAND;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME_BRAND)
public class BrandEntity extends BaseEntity<Long> {

    @Column(name = COLUMN_NAME_NAME)
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = TABLE_NAME_BRAND)
    private List<ProductEntity> products;

    @Builder
    public BrandEntity(Long id, String name, List<ProductEntity> products) {
        super(id);
        this.name = name;
        this.products = products;
    }
}

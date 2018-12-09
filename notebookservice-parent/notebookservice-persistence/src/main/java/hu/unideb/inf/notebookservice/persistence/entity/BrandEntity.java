package hu.unideb.inf.notebookservice.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

import static hu.unideb.inf.notebookservice.commons.table.ColumnName.BrandColumName.COLUMN_NAME_NAME;
import static hu.unideb.inf.notebookservice.commons.table.TableName.TABLE_NAME_BRAND;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"products"})
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME_BRAND)
public class BrandEntity extends BaseEntity<Long> {

    @Column(name = COLUMN_NAME_NAME)
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = TABLE_NAME_BRAND)
    private List<ProductEntity> products;

    @Builder
    public BrandEntity(Long id, String name) {
        super(id);
        this.name = name;
    }
}

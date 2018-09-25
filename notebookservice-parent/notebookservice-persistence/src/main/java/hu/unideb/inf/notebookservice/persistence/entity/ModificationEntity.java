package hu.unideb.inf.notebookservice.persistence.entity;

import lombok.*;

import javax.persistence.*;

import java.util.List;

import static hu.unideb.inf.notebookservice.commons.pojo.table.ColumnName.ModificationColumnName.*;
import static hu.unideb.inf.notebookservice.commons.pojo.table.TableName.TABLE_NAME_MODIFICATION;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME_MODIFICATION)
public class ModificationEntity extends BaseEntity<Long> {

    @Column(name = COLUMN_NAME_NAME)
    private String name;

    @Column(name = COLUMN_NAME_PRICE)
    private int price;

    @ManyToMany(mappedBy = "modifications",fetch = FetchType.LAZY)
    private List<ServiceEntity> servicies;

    @Builder
    public ModificationEntity(Long id, String name, int price, List<ServiceEntity> servicies) {
        super(id);
        this.name = name;
        this.price = price;
        this.servicies = servicies;
    }
}

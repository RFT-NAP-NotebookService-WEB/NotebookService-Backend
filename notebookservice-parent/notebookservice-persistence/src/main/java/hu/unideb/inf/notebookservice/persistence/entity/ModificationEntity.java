package hu.unideb.inf.notebookservice.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.List;

import static hu.unideb.inf.notebookservice.commons.table.ColumnName.ModificationColumnName.*;
import static hu.unideb.inf.notebookservice.commons.table.TableName.TABLE_NAME_MODIFICATION;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"maintenances"})
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME_MODIFICATION)
public class ModificationEntity extends BaseEntity<Long> {

    @Column(name = COLUMN_NAME_NAME)
    private String name;

    @Column(name = COLUMN_NAME_PRICE)
    private Long price;

    @ManyToMany(mappedBy = "modifications",fetch = FetchType.LAZY)
    private List<MaintenanceEntity> maintenances;

    @Builder
    public ModificationEntity(Long id, String name, Long price) {
        super(id);
        this.name = name;
        this.price = price;
    }
}

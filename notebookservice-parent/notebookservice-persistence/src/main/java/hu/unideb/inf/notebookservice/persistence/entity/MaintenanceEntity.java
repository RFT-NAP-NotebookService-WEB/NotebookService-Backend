package hu.unideb.inf.notebookservice.persistence.entity;

import hu.unideb.inf.notebookservice.commons.enumeration.Status;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.LocalDate;
import java.util.List;

import static hu.unideb.inf.notebookservice.commons.table.ColumnName.ModificationColumnName.COLUMN_NAME_MODIFICATION_ID;
import static hu.unideb.inf.notebookservice.commons.table.ColumnName.ProductColumnName.COLUMN_NAME_PRODUCT_ID;
import static hu.unideb.inf.notebookservice.commons.table.ColumnName.ReferencedColumName.REFERENCED_COLUM_NAME_ID;
import static hu.unideb.inf.notebookservice.commons.table.ColumnName.MaintenanceColumnName.*;
import static hu.unideb.inf.notebookservice.commons.table.ColumnName.UserColumnName.COLUMN_NAME_USER_ID;
import static hu.unideb.inf.notebookservice.commons.table.TableName.TABLE_NAME_MAINTENANCE;
import static hu.unideb.inf.notebookservice.commons.table.TableName.TABLE_NAME_MAINTENANCE_HAS_MODIFICATION;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"user", "product", "modifications"})
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME_MAINTENANCE)
public class MaintenanceEntity extends BaseEntity<Long> {

    @Column(name = COLUMN_NAME_START_DATE)
    private LocalDate startDate;

    @Column(name = COLUMN_NAME_END_DATE)
    private LocalDate endDate;

    @Column(name = COLUMN_NAME_STATUS)
    private Status status;

    @Column(name = COLUMN_NAME_FAULT)
    private String fault;

    @ManyToOne
    @JoinColumn(name = COLUMN_NAME_USER_ID, nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID, nullable = false)
    private ProductEntity product;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = TABLE_NAME_MAINTENANCE_HAS_MODIFICATION,
            joinColumns = @JoinColumn(name = COLUMN_NAME_MODIFICATION_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID),
            inverseJoinColumns = @JoinColumn(name = COLUMN_NAME_MAINTENANCE_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID))
    private List<ModificationEntity> modifications;

    @Builder
    public MaintenanceEntity(Long id, LocalDate startDate, LocalDate endDate, Status status, String fault, UserEntity user, ProductEntity product, List<ModificationEntity> modifications) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.fault = fault;
        this.user = user;
        this.product = product;
        this.modifications = modifications;
    }
}

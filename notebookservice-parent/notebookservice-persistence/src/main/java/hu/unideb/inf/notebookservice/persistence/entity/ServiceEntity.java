package hu.unideb.inf.notebookservice.persistence.entity;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

import static hu.unideb.inf.notebookservice.commons.pojo.table.ColumnName.ModificationColumnName.COLUMN_NAME_MODIFICATION_ID;
import static hu.unideb.inf.notebookservice.commons.pojo.table.ColumnName.ProductColumnName.COLUMN_NAME_PRODUCT_ID;
import static hu.unideb.inf.notebookservice.commons.pojo.table.ColumnName.ReferencedColumName.REFERENCED_COLUM_NAME_ID;
import static hu.unideb.inf.notebookservice.commons.pojo.table.ColumnName.ServiceColumnName.*;
import static hu.unideb.inf.notebookservice.commons.pojo.table.ColumnName.UserColumnName.COLUMN_NAME_USER_ID;
import static hu.unideb.inf.notebookservice.commons.pojo.table.TableName.TABLE_NAME_SERVICE;
import static hu.unideb.inf.notebookservice.commons.pojo.table.TableName.TABLE_NAME_SERVICE_HAS_MODIFICATION;
import static javax.persistence.EnumType.STRING;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME_SERVICE)
public class ServiceEntity extends BaseEntity<Long> {

    @Column(name = COLUMN_NAME_START_DATE)
    private LocalDate startDate;

    @Column(name = COLUMN_NAME_END_DATE)
    private LocalDate endDate;

    @Column(name = COLUMN_NAME_STATUS)
    @Enumerated(value = STRING)
    private StatusEntity status;

    @Column(name = COLUMN_NAME_FAULT)
    private String fault;

    @ManyToOne
    @JoinColumn(name = COLUMN_NAME_USER_ID, nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID, nullable = false)
    private ProductEntity product;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = TABLE_NAME_SERVICE_HAS_MODIFICATION,
            joinColumns = @JoinColumn(name = COLUMN_NAME_MODIFICATION_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID),
            inverseJoinColumns = @JoinColumn(name = COLUMN_NAME_SERVICE_ID, referencedColumnName = REFERENCED_COLUM_NAME_ID))
    private List<ModificationEntity> modifications;

    @Builder
    public ServiceEntity(Long id, LocalDate startDate, LocalDate endDate, StatusEntity status, String fault, UserEntity user, ProductEntity product, List<ModificationEntity> modifications) {
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

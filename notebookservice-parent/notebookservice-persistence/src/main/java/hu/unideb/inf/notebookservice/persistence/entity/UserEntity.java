package hu.unideb.inf.notebookservice.persistence.entity;

import hu.unideb.inf.notebookservice.commons.enumeration.UserRole;
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
import javax.persistence.UniqueConstraint;
import java.util.List;

import static hu.unideb.inf.notebookservice.commons.table.ColumnName.UserColumnName.COLUMN_NAME_PASSWORD;
import static hu.unideb.inf.notebookservice.commons.table.ColumnName.UserColumnName.COLUMN_NAME_ROLE;
import static hu.unideb.inf.notebookservice.commons.table.ColumnName.UserColumnName.COLUMN_NAME_USERNAME;
import static hu.unideb.inf.notebookservice.commons.table.TableName.TABLE_NAME_USER;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = "password")
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME_USER, uniqueConstraints = @UniqueConstraint(columnNames = COLUMN_NAME_USERNAME))
public class UserEntity extends BaseEntity<Long> {

    @Column(name = COLUMN_NAME_USERNAME)
    private String username;

    @Column(name = COLUMN_NAME_PASSWORD)
    private String password;

    @Column(name = COLUMN_NAME_ROLE)
    private UserRole userRole;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = TABLE_NAME_USER)
    private List<MaintenanceEntity> maintenances;

    @Builder
    public UserEntity(Long id, String username, String password, UserRole userRole) {
        super(id);
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }
}

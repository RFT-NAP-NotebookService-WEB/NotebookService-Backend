package hu.unideb.inf.notebookservice.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static hu.unideb.inf.notebookservice.commons.pojo.table.ColumnName.UserColumnName.*;
import static hu.unideb.inf.notebookservice.commons.pojo.table.TableName.TABLE_NAME_USER;
import static javax.persistence.EnumType.STRING;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME_USER, uniqueConstraints = @UniqueConstraint(columnNames = COLUMN_NAME_USERNAME))
public class UserEntity extends BaseEntity<Long> {

    @Column(name = COLUMN_NAME_USERNAME)
    private String username;

    @Column(name = COLUMN_NAME_PASSWORD)
    private String password;

    @Column(name = COLUMN_NAME_ROLE)
    @Enumerated(value = STRING)
    private UserRoleEntity userRole;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = TABLE_NAME_USER)
    private List<ServiceEntity> services;

    @Builder
    public UserEntity(Long id, String username, String password, UserRoleEntity userRole, List<ServiceEntity> services) {
        super(id);
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.services = services;
    }
}

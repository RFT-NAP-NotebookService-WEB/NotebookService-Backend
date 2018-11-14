package hu.unideb.inf.notebookservice.persistence.entity;

import lombok.*;

import javax.persistence.*;

import java.util.List;

import static hu.unideb.inf.notebookservice.commons.pojo.table.ColumnName.ClientColumnName.*;
import static hu.unideb.inf.notebookservice.commons.pojo.table.TableName.TABLE_NAME_CLIENT;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME_CLIENT)
public class ClientEntity extends BaseEntity<Long> {

    @Column(name = COLUMN_NAME_FIRST_NAME)
    private String firstName;

    @Column(name = COLUMN_NAME_LAST_NAME)
    private String lastName;

    @Column(name = COLUMN_NAME_EMAIL)
    private String email;

    @Column(name = COLUMN_NAME_PHONE)
    private String phone;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = TABLE_NAME_CLIENT)
    private List<ProductEntity> products;

    @Builder
    public ClientEntity(Long id, String firstName, String lastName, String email, String phone) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}

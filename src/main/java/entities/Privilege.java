package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Privilege implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private PrivilegeName name;

    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;


}

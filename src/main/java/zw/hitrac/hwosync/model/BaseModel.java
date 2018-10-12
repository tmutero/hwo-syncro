package zw.hitrac.hwosync.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tmutero.
 */
@MappedSuperclass
public class BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Transient
    public boolean isNew() {
        return (getId() == null);
    }


}

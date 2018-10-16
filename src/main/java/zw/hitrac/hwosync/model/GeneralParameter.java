package zw.hitrac.hwosync.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "general_parameter")
public class GeneralParameter extends BaseModel {

    private String disclamer;
    private Long percentageAccuracy;
    private String council;

    public String getCouncil() {
        return council;
    }

    public void setCouncil(String council) {
        this.council = council;
    }

    public String getDisclamer() {
        return disclamer;
    }

    public void setDisclamer(String disclamer) {
        this.disclamer = disclamer;
    }

    public Long getPercentageAccuracy() {
        return percentageAccuracy;
    }

    public void setPercentageAccuracy(Long percentageAccuracy) {
        this.percentageAccuracy = percentageAccuracy;
    }
}

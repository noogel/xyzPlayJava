package noogel.xyz.model;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class BaseLineModel {
    public static final String TABLE = "baseline";

    private Integer id;
    private String report_name;
    private Integer report_period;
    private LocalDateTime creation_date;
}

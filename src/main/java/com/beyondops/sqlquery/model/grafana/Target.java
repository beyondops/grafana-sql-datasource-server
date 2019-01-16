package com.beyondops.sqlquery.model.grafana;

import java.io.Serializable;
import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/16.
 */

@Data
public class Target implements Serializable {

    private String target;
    private String refId;
    private String type;

}

package org.jleopard.ihotel.entity;

import lombok.Data;
import org.jleopard.core.EnumId;
import org.jleopard.core.annotation.Column;
import org.jleopard.core.annotation.Table;

@Data
@Table
public class OrderDetail {

    @Column(id = EnumId.AUTO)
	private Integer id;

    @Column
    private Integer foodCount;

    @Column(join = Orders.class)
    private Orders orderid;

    @Column(value = "food_id",join = Food.class)
    private Food food;
}

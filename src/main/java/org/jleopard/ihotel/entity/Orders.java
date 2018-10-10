package org.jleopard.ihotel.entity;

import lombok.Data;
import org.jleopard.core.EnumId;
import org.jleopard.core.annotation.Column;
import org.jleopard.core.annotation.OneToMany;
import org.jleopard.core.annotation.Table;

import java.util.Date;
import java.util.List;

@Data
@Table
public class Orders {

   @Column(id = EnumId.AUTO)
   private Integer id;

   @Column
   private Double totalPrice;

   @Column
   private Byte orderStatus;

   @Column
   private Date orderDate;

   @Column(value = "table_id",join = DinnerTable.class)
   private DinnerTable table;

   @OneToMany(join = OrderDetail.class)
   private List<OrderDetail> orderDetailList;

}
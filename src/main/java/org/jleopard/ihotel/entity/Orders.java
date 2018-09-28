package org.jleopard.ihotel.entity;

import lombok.Data;
import org.jleopard.core.EnumId;
import org.jleopard.core.annotation.Column;
import org.jleopard.core.annotation.Table;

import java.util.Date;

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

   @Column(join = DinnerTable.class)
   private DinnerTable table_id;

}
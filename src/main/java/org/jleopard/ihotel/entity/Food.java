package org.jleopard.ihotel.entity;

import lombok.*;
import org.jleopard.core.EnumId;
import org.jleopard.core.annotation.Column;
import org.jleopard.core.annotation.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table
public class Food {

	@Column(id = EnumId.AUTO)
	private Integer id;// INT PRIMARY KEY AUTO_INCREMENT,

	@Column
	private String foodName;

	@Column
	private Double price;

	@Column
	private Double mprice;

	@Column(allowNull = true)
	private String remark;

	@Column(allowNull = true)
	private String img;

	@Column(join = FoodType.class)
	private FoodType foodType_id;

	@Override
	public int hashCode() {
		return this.id;
	}
	@Override
	public boolean equals(Object obj) {
		Food f=(Food)obj;
		return f.getId()==this.id;
	}
	
}

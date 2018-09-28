package org.jleopard.ihotel.entity;

import lombok.Data;
import org.jleopard.core.EnumId;
import org.jleopard.core.annotation.Column;
import org.jleopard.core.annotation.Table;

@Data
@Table
public class FoodType {

	@Column(id = EnumId.AUTO)
	private Integer id;

	@Column
	private String typeName;

	public FoodType initId(Integer id){
		this.id = id;
		return this;
	}
	
}

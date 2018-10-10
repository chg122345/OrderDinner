package org.jleopard.ihotel.entity;

import lombok.*;
import org.jleopard.core.EnumId;
import org.jleopard.core.annotation.Column;
import org.jleopard.core.annotation.Table;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class DinnerTable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(id = EnumId.YES)
	private Integer id;

	@Column
	private String tableName;

	@Column
	private Byte tableStatus;

	@Column
	private Date orderDate;

	public DinnerTable(Integer id) {
		this.id = id;
	}
}

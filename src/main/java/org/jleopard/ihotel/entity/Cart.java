/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-10-10  下午3:03
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private Integer targetId;

    private Food food;

    private Integer number;

    private Double total;

    public Cart(Integer targetId, Food food, Integer number) {
        this.targetId = targetId;
        this.food = food;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(targetId, cart.targetId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetId);
    }

    public Double getTotal() {
        return (this.food.getPrice() * this.number);
    }
}

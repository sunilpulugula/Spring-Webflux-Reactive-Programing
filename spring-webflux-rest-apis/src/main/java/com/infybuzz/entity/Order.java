package com.infybuzz.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("orders")
public class Order {

	@Id
	@Column("id")
	private Long id;

	@Column("amount")
	private Double amount;

	@Column("placed_date")
	private LocalDateTime placedDate;

	public Order(Double amount, LocalDateTime placedDate) {
		this.amount = amount;
		this.placedDate = placedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getPlacedDate() {
		return placedDate;
	}

	public void setPlacedDate(LocalDateTime placedDate) {
		this.placedDate = placedDate;
	}

}

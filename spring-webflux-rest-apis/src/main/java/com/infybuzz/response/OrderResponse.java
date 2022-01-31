package com.infybuzz.response;

import java.time.LocalDateTime;

public class OrderResponse {

	private Long id;

	private Double amount;

	private LocalDateTime placedDate;

	public OrderResponse(Long id, Double amount, LocalDateTime placedDate) {
		this.id = id;
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

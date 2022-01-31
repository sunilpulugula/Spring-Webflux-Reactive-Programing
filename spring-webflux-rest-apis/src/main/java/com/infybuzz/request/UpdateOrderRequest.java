package com.infybuzz.request;

import java.time.LocalDateTime;

public class UpdateOrderRequest {

	private Double amount;

	private LocalDateTime placedDate;

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

package com.masivian.model;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import com.masivian.utilities.Utilities;

public class RouletteBet implements Serializable {
	@Id
	private Long id;
	private String color;
	private String number;
	private String result;
	private int value;

	public RouletteBet() {
		this.id = Utilities.generateId();
		this.result = "Roulette still open";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

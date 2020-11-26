package com.masivian.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("roulette")
public class Roulette implements Serializable {
	@Id
	private String id;
	private String status;
	
	private List<RouletteBet> betsOfRoulette;
	private RouletteResult result;

	public Roulette() {		
		this.status = "Closed";
		betsOfRoulette = new ArrayList<>();
	}

	public RouletteResult getResult() {
		return result;
	}

	public void setResult(RouletteResult result) {
		this.result = result;
	}

	public List<RouletteBet> getBetsOfRoulette() {
		return betsOfRoulette;
	}

	public void setBetsOfRoulette(ArrayList<RouletteBet> betsOfRoulette) {
		this.betsOfRoulette = betsOfRoulette;
	}

	public void addBet(RouletteBet bet) {
		this.betsOfRoulette.add(bet);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

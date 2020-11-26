package com.masivian.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masivian.model.Roulette;
import com.masivian.model.RouletteBet;
import com.masivian.model.RouletteResult;
import com.masivian.repository.RouletteRepository;
import com.masivian.utilities.Utilities;

@Service
public class RouletteService {
	@Autowired
	private RouletteRepository rouletteRepo;
	
	public String createRoulette() {
		Roulette newRoulette = new Roulette();
		rouletteRepo.save(newRoulette);
		
		return newRoulette.getId();
	}
	
	public String openRoulette(String rouletteId) {
		String response = "Operation denied";
		Roulette roulette = rouletteRepo.findById(rouletteId).orElse(null);
		if (roulette != null) {
			if (!Utilities.rouletteIsOpen(roulette)) {
				roulette.setStatus("Open");
				rouletteRepo.save(roulette);
				response = "Succesful operation";
			}
		}
		
		return response;
	}
	
	public String wagger(String rouletteId, String bet, int value) {
		String response = "";
		Roulette roulette = rouletteRepo.findById(rouletteId).orElse(null);
		if (roulette != null) {
			if (Utilities.rouletteIsOpen(roulette) && Utilities.betIsValid(bet, value)) {
				RouletteBet newBet = new RouletteBet();
				if (Utilities.IsANumber(bet))
					newBet.setNumber(bet);
				else
					newBet.setColor(bet);
				newBet.setValue(value);
				roulette.addBet(newBet);
				rouletteRepo.save(roulette);
				
				response = "The bet was made";
			} else
				response = "The bet wasn't made because roulette is closed or bet is not valid";
		} else
			response = "The bet wasn't made because id of roulette doesn't exist";

		return response;
	}
	
	public Roulette closeRoulette(String rouletteId) {
		Roulette roulette = rouletteRepo.findById(rouletteId).orElse(null);
		if (roulette != null) {
			if (Utilities.rouletteIsOpen(roulette)) {
				roulette.setStatus("Closed");
				RouletteResult rouletteResult = new RouletteResult();
				roulette.setResult(rouletteResult);
				ArrayList<RouletteBet> rouletteBets = (ArrayList<RouletteBet>) roulette.getBetsOfRoulette();
				for (RouletteBet rouletteBet : rouletteBets) {
					rouletteBet.setResult(Utilities.resultOfBet(rouletteBet, rouletteResult));
				}
				roulette.setBetsOfRoulette(rouletteBets);
				rouletteRepo.save(roulette);
				
			}
		}
		
		return roulette;
	}
	
	public List<Roulette> getAllRoulettesWithStatus() {
	return (List<Roulette>) rouletteRepo.findAll();
	}
}

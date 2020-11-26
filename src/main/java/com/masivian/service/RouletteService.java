package com.masivian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masivian.model.Roulette;
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
	
}

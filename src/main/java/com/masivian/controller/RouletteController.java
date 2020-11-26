package com.masivian.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.masivian.model.RouletteBet;
import com.masivian.model.RouletteResult;
import com.masivian.repository.RouletteRepository;
import com.masivian.service.RouletteService;
import com.masivian.utilities.Utilities;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.masivian.model.Roulette;

@RestController
@RequestMapping("/roulettes")
@Tag(name = "roulette", description = "The roulette API")
public class RouletteController {
	@Autowired
	private RouletteRepository rouletteRepo;
	@Autowired
	private RouletteService rouletteService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> createRoulette() {
		String response = rouletteService.createRoulette();

		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@PutMapping(value = "/{rouletteId}/openRoulette")
	public ResponseEntity<String> openRoulette(@PathVariable("rouletteId") final String rouletteId) {
		String response = rouletteService.openRoulette(rouletteId);

		return new ResponseEntity<>(response, response.equalsIgnoreCase("Succesful operation") ? HttpStatus.OK : HttpStatus.NOT_FOUND) ;
	}

	@PutMapping(value = "/{rouletteId}/{bet}/{value}")
	public String wager(@RequestHeader(value = "userId") long userId, @PathVariable("rouletteId") final String rouletteId,
			@PathVariable("bet") final String bet, @PathVariable("value") final int value) {
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

	@PutMapping(value = "/{rouletteId}/closeRoulette")
	public Roulette closeRoulette(@PathVariable("rouletteId") final String rouletteId) {
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

	@GetMapping
	public ResponseEntity<List<Roulette>> getAllRoulettesWithStatus() {
		String response = "";
		List<Roulette> roulettes = (List<Roulette>) rouletteRepo.findAll();
		 return new ResponseEntity<>(roulettes, HttpStatus.OK);
	}
}

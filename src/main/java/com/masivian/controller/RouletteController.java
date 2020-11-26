package com.masivian.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.masivian.service.RouletteService;
import com.masivian.model.Roulette;

@RestController
@RequestMapping("/roulettes")
public class RouletteController {
	
	@Autowired
	private RouletteService rouletteService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> createRoulette() {
		String response = rouletteService.createRoulette();

		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/{rouletteId}/openRoulette", method = RequestMethod.PUT)
	public ResponseEntity<String> openRoulette(@PathVariable("rouletteId") final String rouletteId) {
		String response = rouletteService.openRoulette(rouletteId);

		return new ResponseEntity<>(response, response.equalsIgnoreCase("Succesful operation") ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{rouletteId}/{bet}/{value}/bet", method = RequestMethod.PUT)	
	public ResponseEntity<String> wager(@RequestHeader(value = "userId") long userId, @PathVariable("rouletteId") final String rouletteId,
			@PathVariable("bet") final String bet, @PathVariable("value") final int value) {
		String response = rouletteService.wagger(rouletteId, bet, value);

		return new ResponseEntity<>(response, response.equalsIgnoreCase("The bet was made") ? HttpStatus.OK : HttpStatus.BAD_REQUEST) ;
	}

	@RequestMapping(value = "/{rouletteId}/closeRoulette", method = RequestMethod.PUT)	
	public ResponseEntity<Roulette> closeRoulette(@PathVariable("rouletteId") final String rouletteId) {
		Roulette roulette = rouletteService.closeRoulette(rouletteId);
		
		return new ResponseEntity<>(roulette,roulette!=null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Roulette>> getAllRoulettesWithStatus() {		
		List<Roulette> roulettes = rouletteService.getAllRoulettesWithStatus();
		
		return new ResponseEntity<>(roulettes, HttpStatus.OK);
	}
	
}

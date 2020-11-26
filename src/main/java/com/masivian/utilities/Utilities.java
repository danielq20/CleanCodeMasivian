package com.masivian.utilities;

import java.util.Arrays;
import java.util.List;
import com.masivian.model.Roulette;
import com.masivian.model.RouletteBet;
import com.masivian.model.RouletteResult;

public class Utilities {
	
	public static boolean rouletteIsOpen(Roulette roulette) {
		return roulette.getStatus().equals("Open");
	}

	public static boolean IsANumber(String input) {
		try {
			int number = Integer.parseInt(input);

			return true;
		} catch (Exception e) {

			return false;
		}
	}

	public static String colorForResult(int number) {
		if (number == 0) {

			return "Green";
		} else {
			List<Integer> blackNumbers = Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33,
					36);

			return blackNumbers.contains(number) ? "Black" : "Red";
		}
	}

	public static String resultOfBet(RouletteBet rouletteBet, RouletteResult rouletteResult) {
		if (rouletteBet.getColor() != null) {

			return rouletteBet.getColor().equalsIgnoreCase(rouletteResult.getColor()) ? "Won" : "Lost";
		} else {

			return rouletteBet.getNumber().equals(rouletteResult.getNumber()) ? "Won" : "Lost";
		}
	}

	public static boolean betIsValid(String bet, int value) {
		if (IsANumber(bet))
			
			return (Integer.parseInt(bet) >= 0 && Integer.parseInt(bet) <= 36) && (value >= 1 && value <= 10000);
		else
			
			return (bet.equalsIgnoreCase("red") || bet.equalsIgnoreCase("black")) && (value >= 1 && value <= 10000);
	}
	
}

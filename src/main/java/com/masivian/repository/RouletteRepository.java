package com.masivian.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.masivian.model.Roulette;
@Repository
public interface RouletteRepository extends CrudRepository<Roulette, String> {

}

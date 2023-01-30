package com.challenge.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.challenge.domain.Users;
import com.challenge.services.CoinService;

import jakarta.websocket.server.PathParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/challenge")
public class ChallengeController {

	@Autowired
	private final CoinService service;
	
	public ChallengeController(CoinService service) {
		this.service = service;
	}

	@GetMapping("/users")
	public Flux<Users> getAllUsers() {
		return this.service.getAll();
	}

	@PostMapping("/users")
	public Mono<Users> saveUser(@RequestBody Users data) {
		return this.service.register(data);
	}

	@GetMapping("/convert")
	public ResponseEntity<Object> convertCurrency(
		@PathParam("from") String from,
		@PathParam("to") String to,
		@PathParam("amount") double amount
	) throws IOException {
		double type = service.conversion(from, to, amount) / amount;
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("origen", from);
		response.put("destino", to);
		response.put("monto", amount);
		response.put("resultado", service.conversion(from, to, amount));
		response.put("tipo", String.format("%.6f", type));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/rates")
	public String rates() throws IOException, Exception {
		return this.service.rate();
	}
}
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
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", service.conversion(from, to, amount));
		return ResponseEntity.ok(response);
	}
}
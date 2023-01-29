package com.challenge.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.challenge.services.CoinService;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

	@Autowired
	private final CoinService service;
	
	public ChallengeController(CoinService service) {
		this.service = service;
	}

	@GetMapping("/coins")
	public ResponseEntity<Object> convertCurrency() throws IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", service.conversion("PEN", "USD", 100));
		return ResponseEntity.ok(response);
	}
}
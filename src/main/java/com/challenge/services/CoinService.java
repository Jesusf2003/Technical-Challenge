package com.challenge.services;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.User;

import com.challenge.domain.*;
import com.google.gson.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CoinService {

	private final UsersRepository repo;

	public CoinService(UsersRepository repo) {
		this.repo = repo;
	}

	public Flux<Users> getAll() {
		return this.repo.findAll();
	}

	public Mono<Users> register(Users data) {
		return this.repo.save(data);
	}

	public String conversion(String from, String to, double amount) throws IOException {
		URL url = new URL("https://api.exchangerate.host/convert?from=" + from + "&to="+to+"&amount="+amount);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.connect();

		JsonParser jp = new JsonParser();
		JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
		JsonObject jsonobj = root.getAsJsonObject();

		String req_result = jsonobj.get("result").getAsString();
		return req_result;
	}
}
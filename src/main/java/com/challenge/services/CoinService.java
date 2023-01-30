package com.challenge.services;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.challenge.domain.*;
import com.google.gson.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

	public double conversion(String from, String to, double amount) throws IOException {
		URL url = new URL("https://api.exchangerate.host/convert?from=" + from + "&to=" + to + "&amount=" + amount);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.connect();

		JsonParser jp = new JsonParser();
		JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
		JsonObject jsonobj = root.getAsJsonObject();

		double req_result = jsonobj.get("result").getAsDouble();
		return req_result;
	}
	
	public String rate() throws IOException, Exception {
		String url_str = "https://api.exchangerate.host/latest?base=USD&symbols=PEN";

		URL url = new URL(url_str);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.connect();

		JsonParser jp = new JsonParser();
		JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
		JsonObject jsonobj = root.getAsJsonObject();

		String req_result = jsonobj.get("result").getAsString();
		return req_result;
	}
}
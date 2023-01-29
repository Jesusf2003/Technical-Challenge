package com.challenge.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class CoinService {

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
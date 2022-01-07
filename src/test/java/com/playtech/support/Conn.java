package com.playtech.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

public class Conn {

	private BufferedReader reader;
	private String line;
	private StringBuffer responseContent = new StringBuffer();
	private HttpURLConnection connection;
	private String username = "o4xvhw85";
	private String password = "3feom1tw8fy2v9s665tv";

	private int status;
	private DataC data;

	private void retriveDataFromAPI() {
		try {
			URL url = new URL("https://sheetdb.io/api/v1/dsc2eeq28maan");
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization",
					"Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));

			status = connection.getResponseCode();

			if (status > 299) {
				Assert.assertTrue(
						"Couldn't reach the API for the test data as you may see in the following error: " + status,
						false);
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
			}

		} catch (MalformedURLException e1) {
			Assert.assertTrue("Couldn't reach the API for the test data as you may see in the following error: " + e1,
					false);
		} catch (IOException e2) {
			Assert.assertTrue("Couldn't reach the API for the test data as you may see in the following error: " + e2,
					false);
		} finally {
			connection.disconnect();
		}
	}

	public DataC getData(String lang) {
		if (lang == null) {
			lang = "en";
		}
		retriveDataFromAPI();
		JSONArray items = new JSONArray(responseContent.toString());
		for (int i = 0; i < items.length(); i++) {
			JSONObject item = items.getJSONObject(i);
			if (item.getString("lang").equals(lang)) {
				data = new DataC(item.getString("lang"), item.getString("name"), item.getString("email"),
						item.getString("address"), item.getString("phone"), item.getString("comments"),
						item.getString("submitLocator"), item.getString("clearSelectionLocator"),
						item.getString("message"), item.getString("clearFormLocator"), item.getString("emailMessage"),
						item.getString("allertClearLocator"));
			}
		}

		if (status != 200 || data == null) {
			return null;
		} else {
			return data;
		}
	}

}

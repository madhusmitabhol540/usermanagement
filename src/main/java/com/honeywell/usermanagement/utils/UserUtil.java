package com.honeywell.usermanagement.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.honeywell.usermanagement.models.UserDTO;
import com.honeywell.usermanagement.models.UserDetails;

public class UserUtil {

	public static UserDTO[] getUsersList() {

		JSONParser parser = new JSONParser();
		UserDetails userDetails = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			Object obj = parser.parse(new FileReader(
					"C:\\Madhusmita\\Users.json"));
			JSONObject jsonObject = (JSONObject) obj;
			userDetails = mapper.readValue(jsonObject.toString(),
					UserDetails.class);
			return userDetails.getUserDTO();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}

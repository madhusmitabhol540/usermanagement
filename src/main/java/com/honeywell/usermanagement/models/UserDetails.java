package com.honeywell.usermanagement.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetails {

	@JsonProperty("users")
	private UserDTO[] userDTO;
}

package com.honeywell.usermanagement.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.honeywell.usermanagement.exception.SessionNotExistException;
import com.honeywell.usermanagement.exception.UsernameNotFoundException;
import com.honeywell.usermanagement.models.UserDTO;
import com.honeywell.usermanagement.payload.request.LoginRequest;
import com.honeywell.usermanagement.payload.response.MessageResponse;
import com.honeywell.usermanagement.utils.UserUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@PostMapping("/login")
	public ResponseEntity<MessageResponse> authenticateUser(@RequestBody LoginRequest loginRequest,
			HttpServletRequest request) {
		UserDTO[] userDtoList = UserUtil.getUsersList();
		if (userDtoList != null) {
			Predicate<UserDTO> userName = e -> e.getUsername().equalsIgnoreCase(loginRequest.getUsername());
			Predicate<UserDTO> pass = e -> e.getPassword().equalsIgnoreCase(loginRequest.getPassword());
			long count = Arrays.asList(userDtoList).stream().filter(userName.and(pass)).count();
			if (count > 0) {
				UserDTO messages = (UserDTO) request.getSession().getAttribute("MY_SESSION_MESSAGES");
				if (messages == null) {
					messages = new UserDTO();
					request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
				}
				if (Arrays.asList(userDtoList).stream().findFirst().isPresent()) {
					UserDTO us = Arrays.asList(userDtoList).stream().findFirst().get();
					messages = us;
				}
				request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
				MessageResponse response = new MessageResponse(true, "SUCCESS", count);
				return new ResponseEntity<MessageResponse>(response,
						response.getResponseCode() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
			} else
				throw new UsernameNotFoundException("User Not Found with username: " + loginRequest.getUsername());
		} else {
			return null;
		}
	}

	@GetMapping("/userinfo")
	public UserDTO getUserInfoFromSession(HttpSession session) {
		UserDTO li = (UserDTO) session.getAttribute("MY_SESSION_MESSAGES");
		if (null != li) {
			return li;
		} else {
			throw new SessionNotExistException("Session not exist ");
		}
	}

	@GetMapping("/logout")
	public void logout(HttpServletRequest request) {
		request.getSession().invalidate();
	}
}

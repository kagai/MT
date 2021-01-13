package com.example.app.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.example.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.helper.CSVHelper;
import com.example.app.model.User;
import com.example.app.repository.UserRepository;

@Service
public class CSVService {

	@Autowired
	UserRepository repository;
	
	public void save(MultipartFile file) {
		try {
			List<User> users = CSVHelper.csvToUsers(file.getInputStream());
			repository.saveAll(users);
		} catch (IOException e) {
			throw new RuntimeException("Fail to store csv data: " + e.getMessage());
		}
	}
}

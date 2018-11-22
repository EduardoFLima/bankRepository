package com.challenge.question5.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.challenge.question5.entity.Bank;

public interface BankService {
	
	String saveFile(MultipartFile csvFile);
	
	Bank findById(Long identifier);

}

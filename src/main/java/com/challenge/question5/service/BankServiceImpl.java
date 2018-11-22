package com.challenge.question5.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.challenge.question5.entity.Bank;
import com.challenge.question5.repository.BankRepository;

@Service
public class BankServiceImpl implements BankService {
	
	@Autowired
	BankRepository rep;

	@Override
	public String saveFile(MultipartFile csvFile) {
		
		BufferedReader br;
		List<Bank> bankList = new ArrayList<>();
		
		try {
			 String line;
		     InputStream is = csvFile.getInputStream();
		     br = new BufferedReader(new InputStreamReader(is));
		     
		     boolean first = true;
		     while ((line = br.readLine()) != null) {
		    	 
		    	 if (first) {
		    		 first = false;
		    		 continue;
		    	 }
		    	 
		    	 String[] words = line.split(";");
		         bankList.add(new Bank(new Long(words[1]), words[0]));
		     }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if (bankList != null && bankList.size() > 0) {
			rep.saveAll(bankList);
			return "File uploaded";
		}
		
		return "Error while trying to upload file";		
	}

	@Override
	public Bank findById(Long identifier) {
		
		Optional<Bank> result = rep.findById(identifier);
		
		if (result.isPresent()) 
		    return result.get();
		
		Bank b = new Bank();
		b.setName("Bank not found");
		
		return b;
	}

}

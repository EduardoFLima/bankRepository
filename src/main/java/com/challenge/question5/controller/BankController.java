package com.challenge.question5.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.challenge.question5.entity.Bank;
import com.challenge.question5.service.BankService;

@Controller
public class BankController {

	@Autowired
	private BankService service;

	@GetMapping("/bank")
	public String getBank(Model model) {

		model.addAttribute("bank", new Bank());

		System.out.println("get");

		return "bank";
	}

	@PostMapping("/bank")
	public String bankSubmit(@ModelAttribute @Valid Bank bank, BindingResult bindingResult, Model model) {

		System.out.println("submit");

		if (bindingResult.hasErrors()) {
			System.out.println("BINDING RESULT ERROR");
			return "bank";
		}

		Bank returnedBank = null;
		if (bank != null)
			returnedBank = service.findById(bank.getIdentifier());

		if (returnedBank != null) {
			System.out.println("Identifier - " + returnedBank.getIdentifier());
			System.out.println("name - " + returnedBank.getName());
			model.addAttribute("bank", returnedBank);
		} else
			model.addAttribute("bank", new Bank());

		return "bank";
	}

	@PostMapping("/uploadCsvFile")
	public String uploadFile(Model model, @RequestParam("uploadFile") MultipartFile file) {

		System.out.println("submit");

		String fileUploadResult = null;
				
		if (!file.isEmpty())
			fileUploadResult = service.saveFile(file);

		model.addAttribute("bank", new Bank());
		model.addAttribute("fileUploadResult", fileUploadResult);

		return "bank";
	}

}

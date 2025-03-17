package com.docuflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.docuflow")
public class DocuFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocuFlowApplication.class, args);
	}

}

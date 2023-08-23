package br.com.cesarMontaldi;

import br.com.cesarMontaldi.domain.SocialMetaTag;
import br.com.cesarMontaldi.service.SocialMetaTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CadastroPromocoesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CadastroPromocoesApplication.class, args);
	}

	@Autowired
	SocialMetaTagService service;

	@Override
	public void run(String... args) throws Exception {


	}
}

package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
/* EnableJpaRepositories: Por padrão já vem habilitado, mas como precisamos usar o repositoryBaseClass para definir o
 * CustomJpaRepositoryImpl como base, precisamos informar. */
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodApiApplication.class, args);
	}

}

package com.desktop_spring.desktop;

import com.desktop_spring.desktop.domain.Cliente;
import com.desktop_spring.desktop.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.util.UUID;

@SpringBootApplication
@RequiredArgsConstructor
public class DesktopApplication {

    private final ClienteService clienteService;

	public static void main(String[] args) {
		SpringApplication.run(DesktopApplication.class, args);

	}

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {

            String nome = JOptionPane.showInputDialog("Digite seu nome: ");
            String cpf = JOptionPane.showInputDialog("Digite seu cpf: ");
            String telefone = JOptionPane.showInputDialog("Digite seu telefone: ");

            Cliente cliente = new Cliente(nome,cpf,telefone);

            clienteService.saveClient(cliente);

        };
    }

}

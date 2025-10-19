package com.desktop_spring.desktop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private double valor;
	
	private LocalDateTime dataDeposito;
	
	 public Deposito(double valor) {
		this.valor = valor;
		this.dataDeposito = LocalDateTime.now();
	}

	 public String getDataDepositoFormatada() {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	        return this.dataDeposito.format(formatter);
	    }




	 @Override
	 public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Valor:");
		builder.append(valor);
		builder.append(", Data Deposito:");
		builder.append(getDataDepositoFormatada());
		return builder.toString();
	 }




	 
	 
	 
}

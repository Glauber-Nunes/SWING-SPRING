package com.desktop_spring.desktop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int numero;

    private double saldo;

    private double limite;

    private long score;
    
    private LocalDateTime dataAbertura;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Deposito> depositos = new ArrayList<>();


    //private Cliente cliente;

    public Conta() {
        this.numero = this.gerarNumeroDaConta();
        this.dataAbertura = LocalDateTime.now();
        this.score = 320;
        this.limite = 0;
        this.saldo = 0;
       // this.cliente = cliente;
    }


    public String getDataAberturaFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return this.dataAbertura.format(formatter);
    }

    public int gerarNumeroDaConta() {
        Random gerador = new Random();

        int numeroAleatorio0a9 = gerador.nextInt(10);

        int min = 5;
        int max = 15;
        int numeroAleatorio5a15 = gerador.nextInt((max - min) + 1) + min;

        return numeroAleatorio5a15;
    }
    
    
    
    public List<Deposito> getDepositos() {
		return depositos;
	}


	public void addDeposito(Deposito deposito) {
    	this.depositos.add(deposito);
    }

    @Override
    public String toString() {
        return  "  Numero:" + numero +
                ", Saldo:" + saldo +
                ", Limite:" + limite +
                ", Score:" + score +
                ", Data Abertura:" + getDataAberturaFormatada();
    }
}

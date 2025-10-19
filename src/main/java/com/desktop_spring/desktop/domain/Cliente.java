package com.desktop_spring.desktop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String cpf;

    private String telefone;

    private int qtdMovimentacao;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public Cliente(String nome, String cpf, String telefone) {

        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return
                "  Nome='" + nome + '\'' +
                ", Cpf='" + cpf + '\'' +
                ", Telefone='" + telefone + '\'' +
                ", Conta=" + conta.getNumero() ;
    }
}
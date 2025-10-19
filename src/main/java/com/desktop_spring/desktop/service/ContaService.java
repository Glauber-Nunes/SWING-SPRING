package com.desktop_spring.desktop.service;

import com.desktop_spring.desktop.domain.Cliente;
import com.desktop_spring.desktop.domain.Conta;
import com.desktop_spring.desktop.domain.Deposito;
import com.desktop_spring.desktop.domain.Pix;
import com.desktop_spring.desktop.repository.ContaRepository;
import com.desktop_spring.desktop.repository.DepositoRepository;
import com.desktop_spring.desktop.repository.PixRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContaService {
	
	private final double VALOR_MAXIMO_DEPOSITO = 5000d;
	
	//RECEBE 100 PONTOS NO SCORE
	private final long QUANTIA_AUMENTA_SCORE = 1000;

    private final ContaRepository contaRepository;

    private final DepositoRepository  depositoRepository;

    private final PixRepository pixRepository;

    public void consultarSaldo(Cliente cliente) {


        Conta conta = contaRepository.findById(cliente.getConta().getId()).orElseThrow(
                () -> new RuntimeException("Not Found")
        );

        JOptionPane.showMessageDialog(null, "Seu Saldo é : " + conta.getSaldo());
    }

    @Transactional
    public void consultarScore(Cliente cliente) {

        Conta conta = contaRepository.findById(cliente.getConta().getId()).orElseThrow(
                () -> new RuntimeException("Not Found")
        );

        JOptionPane.showMessageDialog(null, "Seu Score é : " + conta.getScore());
        JOptionPane.showMessageDialog(null, "Use Sua Conta Para Aumentar Seu Score :)");

    }
    
    
    public void consultarConta(Cliente cliente) {
    
    	JOptionPane.showMessageDialog(null, cliente.getConta().toString());

    }

    @Transactional
    public void fazerDeposito(Cliente cliente) {
    	
    	double quantia = Double.parseDouble(JOptionPane.showInputDialog(null, "DIGITE O VALOR R$"));
    	
    	if(quantia > VALOR_MAXIMO_DEPOSITO) {
    		JOptionPane.showMessageDialog(null, "SEU LIMITE DE DEPOSITO É " + VALOR_MAXIMO_DEPOSITO + "R$");
    		fazerDeposito(cliente);
    	}

        Conta conta = contaRepository.findById(cliente.getConta().getId()).orElseThrow(
                () -> new RuntimeException("Not Found")
        );

    	conta.setSaldo(conta.getSaldo() + quantia);

        contaRepository.saveAndFlush(conta);

        JOptionPane.showMessageDialog(null, "DEPOSITO EFETUADO COM SUCESSO");
        this.aumentoAutomaticoScore(quantia,cliente.getConta());

        // adcionando historico de depositos
        Deposito deposito = new Deposito(quantia);

        conta.getDepositos().add(deposito);

        depositoRepository.save(deposito);

    }
    
    public void aumentoAutomaticoScore(double quantia,Conta conta) {

    	
    	if(quantia >= QUANTIA_AUMENTA_SCORE) {
    		conta.setScore(conta.getScore() + 200);
            JOptionPane.showMessageDialog(null, "OBAAA, VOÇE TEVE UM AUMENTO NO SEU SCORE"
            		+ " " + conta.getScore());
            
            this.receberLimiteContaAutomatico(conta);

    	}
    	
    }
    
    public void receberLimiteContaAutomatico(Conta conta) {
    	
    	if(conta.getScore() >= 520) {
    		conta.setLimite(conta.getLimite() + 400);
    		JOptionPane.showMessageDialog(null, "OBAAA, VOÇE TEM UM LIMITE APROVADO PARABÉNS"
            		+ " " + conta.getLimite());
    	}
    	
    }

    @Transactional
    public void historicoDepositos(Cliente cliente) {
    	

        Conta conta = contaRepository.findById(cliente.getConta().getId()).orElseThrow(
                () -> new RuntimeException("Not Found")
        );

        StringBuilder juntaTudoEmUmaUnicaString = new StringBuilder();


        for (Deposito deposito : conta.getDepositos()) {
            juntaTudoEmUmaUnicaString.append(deposito).append("\n");

        }

        StringBuilder exibeFormatadoPulandoUmaLinhaParaCadaDeposito = juntaTudoEmUmaUnicaString;

        JOptionPane.showMessageDialog(null, exibeFormatadoPulandoUmaLinhaParaCadaDeposito.toString());
    	
        //clienteService.menuOptions(cliente);
    }

    @Transactional
    public void fazerPix(Cliente cliente) {

        String chavePix = JOptionPane.showInputDialog(null, "DIGITE A CHAVE PIX");
        double quantia = Double.parseDouble(JOptionPane.showInputDialog(null, "DIGITE O VALOR R$"));


        Conta conta = contaRepository.findById(cliente.getConta().getId()).orElseThrow(
                () -> new RuntimeException("Not Found")
        );

        if (conta.getSaldo() > 0 && quantia <= conta.getSaldo()) {

            conta.setSaldo(conta.getSaldo() - quantia);

            Pix pix = new Pix();
            pix.setValor(quantia);
            pix.setDataPix(LocalDateTime.now());
            pix.setChavePix(chavePix);

            pixRepository.save(pix);

            JOptionPane.showMessageDialog(null, "PIX EFETUADO COM SUCESSO PARA " + chavePix);


            contaRepository.save(conta);

        } else {
            JOptionPane.showMessageDialog(null, "SALDO INSUFICINTE PARA VALOR " + quantia);
        }



    }

}
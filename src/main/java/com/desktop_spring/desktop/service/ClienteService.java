package com.desktop_spring.desktop.service;

import com.desktop_spring.desktop.domain.Cliente;
import com.desktop_spring.desktop.domain.Conta;
import com.desktop_spring.desktop.repository.ClienteRepository;
import com.desktop_spring.desktop.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final ContaRepository contaRepository;

    private final MenuService menuService;

    // inicio de tudo salva o cliente e sua conta
    public void saveClient(Cliente cliente){

        JOptionPane.showMessageDialog(null, "OLÁ " + cliente.getNome() + " SEJA BEM VINDO AO MAC-BAN");

        Conta conta = contaRepository.save(new Conta());

        cliente.setConta(conta);

        clienteRepository.save(cliente);

        menuService.menuOptions(cliente);
       
    }

    public void exibirDadosUsuario(Cliente cliente){

        JOptionPane.showMessageDialog(null,cliente.toString());

        //clienteService.menuOptions(cliente);
    }
}

package com.desktop_spring.desktop.service;

import com.desktop_spring.desktop.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final ContaService contaService;

    public void menuOptions(Cliente cliente) {

        int op = Integer.valueOf(JOptionPane.showInputDialog(null,"[1] CONSULTAR SALDO [2] CONSULTAR CONTA [3] DEPOSITAR "
                + "[4] CONSULTAR DEPOSITOS [5] FAZER PIX"));

        switch (op) {
            case 1:
                contaService.consultarSaldo(cliente);
                this.menuOptions(cliente);
                break;

            case 2:
                contaService.consultarConta(cliente);
                this.menuOptions(cliente);
                break;

            case 3:
                contaService.fazerDeposito(cliente);
                this.menuOptions(cliente);
                break;

            case 4:
                contaService.historicoDepositos(cliente);
                this.menuOptions(cliente);
                break;

            case 5:
                contaService.fazerPix(cliente);
                this.menuOptions(cliente);
                break;

            default:
                JOptionPane.showMessageDialog(null,"OPÇAO INVALIDA");
                this.menuOptions(cliente);
                break;

        }

    }
}

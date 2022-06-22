/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Carro;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author bruno
 */
@Stateful
public class CarroDAO<TIPO> extends DAOGenerico<Carro> implements Serializable {
    
    public CarroDAO() {
        super();
        classePersistente = Carro.class;
        // lista de ordenações possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("placa", "Placa", "like"));
        listaOrdem.add(new Ordem("pessoa.nome", "Pessoa", "like"));
        // ordem atual
        ordemAtual = listaOrdem.get(0);
        // inicializar o conversor de ordem com a lista de ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Corretor;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author bruno
 */
@Stateful
public class CorretorDAO<TIPO> extends DAOGenerico<Corretor> implements Serializable {
    
    public CorretorDAO() {
        super();
        classePersistente = Corretor.class;
        // lista de ordenações possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        listaOrdem.add(new Ordem("cpf", "CPF", "like"));
        // ordem atual
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor de ordem com a lista de ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
}

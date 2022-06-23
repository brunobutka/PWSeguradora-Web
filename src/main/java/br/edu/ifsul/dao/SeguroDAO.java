/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Seguro;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author bruno
 */
@Stateful
public class SeguroDAO<TIPO> extends DAOGenerico<Seguro> implements Serializable {
    
    public SeguroDAO() {
        super();
        classePersistente = Seguro.class;
        // lista de ordenações possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("valorTotal", "Valor Total", "="));
        listaOrdem.add(new Ordem("corretor.nome", "Corretor", "like"));
        listaOrdem.add(new Ordem("carro.placa", "Placa", "like"));
        // ordem atual
        ordemAtual = listaOrdem.get(0);
        // inicializar o conversor de ordem com a lista de ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
    @Override
    public Seguro getObjectByID(Object id) throws Exception {
        Seguro obj = em.find(Seguro.class, id);
        obj.getSinistros().size();
        obj.getCoberturas().size();
        return obj;
    }
    
}

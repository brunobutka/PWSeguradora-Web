/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Permissao;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author bruno
 */
@Stateful
public class PermissaoDAO<TIPO> extends DAOGenerico<Permissao> implements Serializable {
    
    public PermissaoDAO() {
        super();
        classePersistente = Permissao.class;
        // lista de ordenações possíveis
        listaOrdem.add(new Ordem("nome", "nome", "like"));
        listaOrdem.add(new Ordem("descricao", "Descrição", "like"));
        // ordem atual
        ordemAtual = listaOrdem.get(0);
        // inicializar o conversor de ordem com a lista de ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
}

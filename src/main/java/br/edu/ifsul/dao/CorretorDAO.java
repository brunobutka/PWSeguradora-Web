/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Corretor;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

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
        ordemAtual = listaOrdem.get(0);
        // inicializar o conversor de ordem com a lista de ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
        
    }
    
    @Override
    public Corretor getObjectByID(Object id) throws Exception {
        Corretor obj = em.find(Corretor.class, id);
        obj.getPermissoes().size();
        return obj;
    }
    
    public Corretor verificaNomeUsuario(String nomeUsuario) throws Exception {
        String jpql = "from Corretor where nomeUsuario = :pNomeUsuario";
        Query query = em.createQuery(jpql);
        query.setParameter("pNomeUsuario", nomeUsuario);
        
        Corretor c  = (Corretor) query.getSingleResult();
        
        if (c == null){
            return null;
        } else {
            return (Corretor) c;
        }
    }
    
    public boolean verificaUnicidadeNomeUsuario(String nomeUsuario) throws Exception {
        String jpql = "from Corretor where nomeUsuario = :pNomeUsuario";
        Query query = em.createQuery(jpql);
        query.setParameter("pNomeUsuario", nomeUsuario);
        if (query.getResultList().size() > 0){
            return false;
        } else {
            return true;
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CarroDAO;
import br.edu.ifsul.dao.CorretorDAO;
import br.edu.ifsul.dao.SeguroDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Carro;
import br.edu.ifsul.modelo.Corretor;
import br.edu.ifsul.modelo.Seguro;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.Util;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author bruno
 */
@Named(value = "controleSeguro")
@ViewScoped
public class ControleSeguro implements Serializable {
    
    @EJB
    private SeguroDAO<Seguro> dao;
    private Seguro objeto;
    @EJB
    private CorretorDAO<Corretor> daoCorretor;
    @EJB
    private CarroDAO<Carro> daoCarro;
    
    public ControleSeguro() {
        
    }

    public String listar() {
        return "/privado/seguro/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Seguro();
    }
    
    public void alterar(Object id) {
        try {
            objeto = dao.getObjectByID(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
       try {
           if (objeto.getId() == null){
               dao.persist(objeto);
           } else {
               dao.merge(objeto);
           }
           Util.mensagemInformacao("Objeto persistido com sucesso!");
       } catch (Exception e){
           Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
       }
    }
    
    public SeguroDAO<Seguro> getDao() {
        return dao;
    }

    public void setDao(SeguroDAO<Seguro> dao) {
        this.dao = dao;
    }

    public Seguro getObjeto() {
        return objeto;
    }

    public void setObjeto(Seguro objeto) {
        this.objeto = objeto;
    }

    public CorretorDAO<Corretor> getDaoCorretor() {
        return daoCorretor;
    }

    public void setDaoCorretor(CorretorDAO<Corretor> daoCorretor) {
        this.daoCorretor = daoCorretor;
    }

    public CarroDAO<Carro> getDaoCarro() {
        return daoCarro;
    }

    public void setDaoCarro(CarroDAO<Carro> daoCarro) {
        this.daoCarro = daoCarro;
    }
    
}

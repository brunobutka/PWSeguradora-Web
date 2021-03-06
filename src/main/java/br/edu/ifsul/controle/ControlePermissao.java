/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PermissaoDAO;
import br.edu.ifsul.modelo.Permissao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author bruno
 */
@Named(value = "controlePermissao")
@ViewScoped
public class ControlePermissao implements Serializable {
    
    @EJB
    private PermissaoDAO<Permissao> dao;
    private Permissao objeto;
    private Boolean novo;
    
    public ControlePermissao() {
        
    }

    public String listar() {
        return "/privado/permissao/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Permissao();
        novo = true;
    }
    
    public void alterar(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            novo = false;
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
           if (novo){
               dao.persist(objeto);
           } else {
               dao.merge(objeto);
           }
           Util.mensagemInformacao("Objeto persistido com sucesso!");
       } catch (Exception e){
           Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
       }
    }
    
    public PermissaoDAO<Permissao> getDao() {
        return dao;
    }

    public void setDao(PermissaoDAO<Permissao> dao) {
        this.dao = dao;
    }

    public Permissao getObjeto() {
        return objeto;
    }

    public void setObjeto(Permissao objeto) {
        this.objeto = objeto;
    }

    public Boolean getNovo() {
        return novo;
    }

    public void setNovo(Boolean novo) {
        this.novo = novo;
    }
    
}

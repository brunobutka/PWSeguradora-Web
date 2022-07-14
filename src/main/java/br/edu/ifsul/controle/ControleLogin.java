/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CorretorDAO;
import br.edu.ifsul.modelo.Corretor;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author bruno
 */
@Named(value = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {
    
    private Corretor corretorAutenticado;
    @EJB
    private CorretorDAO<Corretor> dao;
    private String usuario;
    private String senha;
    
    public ControleLogin(){

    }
    
    public String irTelaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public String efetuarLogin(){
        try {
            HttpServletRequest request = (HttpServletRequest) 
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(usuario, senha);
            if (request.getUserPrincipal() != null){
                corretorAutenticado = 
                        dao.verificaNomeUsuario(request.getUserPrincipal().getName());
                Util.mensagemInformacao("Corretor autenticado com sucesso");
                usuario = "";
                senha = "";
            }            
            return "/index?faces-redirect=true";
        } catch (Exception e){
            Util.mensagemErro("Erro no login: " + Util.getMensagemErro(e));
            return "/login?faces-redirect=true";
        }
    }
    
    public String efetuarLogout(){
        try {
            corretorAutenticado = null;
            HttpServletRequest request = (HttpServletRequest) 
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.logout();          
            Util.mensagemInformacao("Logout realizado com sucesso");
            return "/index?faces-redirect=true";
        } catch (Exception e){
            Util.mensagemErro("Erro no logout: " + Util.getMensagemErro(e));
            return "/login?faces-redirect=true";
        }
    } 
    
    public Corretor getCorretorAutenticado() {
        return corretorAutenticado;
    }

    public void setCorretorAutenticado(Corretor corretorAutenticado) {
        this.corretorAutenticado = corretorAutenticado;
    }

    public CorretorDAO<Corretor> getDao() {
        return dao;
    }

    public void setDao(CorretorDAO<Corretor> dao) {
        this.dao = dao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

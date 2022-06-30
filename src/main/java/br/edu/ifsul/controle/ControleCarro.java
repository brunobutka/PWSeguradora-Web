/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AcessoriosDAO;
import br.edu.ifsul.dao.CarroDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Acessorios;
import br.edu.ifsul.modelo.Carro;
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
@Named(value = "controleCarro")
@ViewScoped
public class ControleCarro implements Serializable {

    @EJB
    private CarroDAO<Carro> dao;
    private Carro objeto;
    private Boolean novo;
    @EJB
    private PessoaDAO<Pessoa> daoPessoa;
    @EJB
    private AcessoriosDAO<Acessorios> daoAcessorios;
    private Acessorios acessorios;
    private int AbaAtiva;

    public ControleCarro() {

    }

    public void removerAcessorio(Acessorios obj) {
        objeto.getAcessorios().remove(obj);
        Util.mensagemInformacao("Acessório removido com sucesso!");
    }

    public void adicionarAcessorio() {
        if (acessorios != null) {
            if (!objeto.getAcessorios().contains(acessorios)) {
                objeto.getAcessorios().add(acessorios);
                Util.mensagemInformacao("Acessório adicionado com sucesso!");
            } else {
                Util.mensagemErro("Usuário já possui este acessório");
            }
        } else {
            Util.mensagemErro("Selecione um registro!");
        }
    }

    public String listar() {
        return "/privado/carro/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Carro();
        novo = true;
        AbaAtiva = 0;
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            novo = false;
            AbaAtiva = 0;
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

    public void salvar() {
        try {
            if (novo) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public CarroDAO<Carro> getDao() {
        return dao;
    }

    public void setDao(CarroDAO<Carro> dao) {
        this.dao = dao;
    }

    public Carro getObjeto() {
        return objeto;
    }

    public void setObjeto(Carro objeto) {
        this.objeto = objeto;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    public Boolean getNovo() {
        return novo;
    }

    public void setNovo(Boolean novo) {
        this.novo = novo;
    }

    public AcessoriosDAO<Acessorios> getDaoAcessorios() {
        return daoAcessorios;
    }

    public void setDaoAcessorios(AcessoriosDAO<Acessorios> daoAcessorios) {
        this.daoAcessorios = daoAcessorios;
    }

    public Acessorios getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(Acessorios acessorios) {
        this.acessorios = acessorios;
    }

    public int getAbaAtiva() {
        return AbaAtiva;
    }

    public void setAbaAtiva(int AbaAtiva) {
        this.AbaAtiva = AbaAtiva;
    }

}

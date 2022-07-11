/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CorretorDAO;
import br.edu.ifsul.dao.PermissaoDAO;
import br.edu.ifsul.modelo.Corretor;
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
@Named(value = "controleCorretor")
@ViewScoped
public class ControleCorretor implements Serializable {

    @EJB
    private CorretorDAO<Corretor> dao;
    private Corretor objeto;
    private Boolean novo;
    @EJB
    private PermissaoDAO<Permissao> daoPermissao;
    private Permissao permissao;
    private int abaAtiva;

    public ControleCorretor() {

    }

    public void removerPermissao(Permissao obj) {
        objeto.getPermissoes().remove(obj);
        Util.mensagemInformacao("Permissão removida com sucesso!");
    }

    public void adicionarPermissao() {
        if (permissao != null) {
            if (!objeto.getPermissoes().contains(permissao)) {
                objeto.getPermissoes().add(permissao);
                Util.mensagemInformacao("Permissão adicionada com sucesso!");
            } else {
                Util.mensagemErro("Usuário já possui esta permissão");
            }
        } else {
            Util.mensagemErro("Selecione um registro!");
        }
    }

    public String listar() {
        return "/privado/corretor/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Corretor();
        novo = true;
        abaAtiva = 0;
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            novo = false;
            abaAtiva = 0;
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
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public CorretorDAO<Corretor> getDao() {
        return dao;
    }

    public void setDao(CorretorDAO<Corretor> dao) {
        this.dao = dao;
    }

    public Corretor getObjeto() {
        return objeto;
    }

    public void setObjeto(Corretor objeto) {
        this.objeto = objeto;
    }

    public Boolean getNovo() {
        return novo;
    }

    public void setNovo(Boolean novo) {
        this.novo = novo;
    }

    public PermissaoDAO<Permissao> getDaoPermissao() {
        return daoPermissao;
    }

    public void setDaoPermissao(PermissaoDAO<Permissao> daoPermissao) {
        this.daoPermissao = daoPermissao;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public int getAbaAtiva() {
        return abaAtiva;
    }

    public void setAbaAtiva(int abaAtiva) {
        this.abaAtiva = abaAtiva;
    }

}

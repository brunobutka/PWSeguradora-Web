/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CarroDAO;
import br.edu.ifsul.dao.CorretorDAO;
import br.edu.ifsul.dao.SeguroDAO;
import br.edu.ifsul.modelo.Carro;
import br.edu.ifsul.modelo.Cobertura;
import br.edu.ifsul.modelo.Corretor;
import br.edu.ifsul.modelo.Seguro;
import br.edu.ifsul.modelo.Sinistro;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private Cobertura cobertura;
    private Boolean novaCobertura;
    private Sinistro sinistro;
    private Boolean novoSinistro;
    private int abaAtiva;
    
    public ControleSeguro() {
        
    }
    
    public void imprimeSeguros() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioSeguros", parametros, dao.getListaTodos());
    }
    
    public void imprimeSeguro(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            List<Seguro> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatorioSeguros", parametros, lista);
            abaAtiva = 0;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao imprimir objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void novaCobertura() {
        cobertura = new Cobertura();
        novaCobertura = true;
    }
    
    public void alterarCobertura(int index) {
        cobertura = objeto.getCoberturas().get(index);
        novaCobertura = false;
    }
    
    public void salvarCobertura() {
        if(novaCobertura) {
            objeto.adicionarCobertura(cobertura);
        }
        Util.mensagemInformacao("Cobertura adicionada ou alterada com sucesso!");
    }
    
    public void removerCobertura(int index) {
        objeto.removerCobertura(index);
        Util.mensagemInformacao("Cobertura removida com sucesso!");
    }
    
    public void novoSinistro() {
        sinistro = new Sinistro();
        novoSinistro = true;
    }
    
    public void alterarSinistro(int index) {
        sinistro = objeto.getSinistros().get(index);
        novoSinistro = false;
    }
    
    public void salvarSinistro() {
        if(novoSinistro) {
            objeto.adicionarSinistro(sinistro);
        }
        Util.mensagemInformacao("Sinistro adicionado ou alterado com sucesso!");
    }
    
    public void removerSinistro(int index) {
        objeto.removerSinistro(index);
        Util.mensagemInformacao("Sinistro removido com sucesso!");
    }

    public String listar() {
        return "/privado/seguro/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Seguro();
        abaAtiva = 0;
    }
    
    public void alterar(Object id) {
        try {
            objeto = dao.getObjectByID(id);
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

    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    public Boolean getNovaCobertura() {
        return novaCobertura;
    }

    public void setNovaCobertura(Boolean novaCobertura) {
        this.novaCobertura = novaCobertura;
    }

    public Sinistro getSinistro() {
        return sinistro;
    }

    public void setSinistro(Sinistro sinistro) {
        this.sinistro = sinistro;
    }

    public Boolean getNovoSinistro() {
        return novoSinistro;
    }

    public void setNovoSinistro(Boolean novoSinistro) {
        this.novoSinistro = novoSinistro;
    }

    public int getAbaAtiva() {
        return abaAtiva;
    }

    public void setAbaAtiva(int abaAtiva) {
        this.abaAtiva = abaAtiva;
    }
    
}

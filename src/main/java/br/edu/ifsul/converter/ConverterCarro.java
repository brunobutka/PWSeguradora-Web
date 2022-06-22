/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.converter;

import br.edu.ifsul.modelo.Carro;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bruno
 */
@Named(value = "converterCarro")
@RequestScoped
public class ConverterCarro implements Serializable, Converter{
    
    @PersistenceContext(unitName = "PWSeguradora-WebPU")
    private EntityManager em;
    
    // da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Carro.class, Integer.parseInt(string));
    }

    // converte da objeto para tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        Carro obj = (Carro) t;
        return obj.getId().toString();
    }
}

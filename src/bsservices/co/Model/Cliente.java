/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsservices.co.Model;

import bsservices.co.Dao.ClienteDAO;
import bsservices.co.Factory.Help;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Button;

/**
 *
 * @author MonTech
 */
public class Cliente {
    private int idCliente;
    private String nome;
    private Date data_nascimento;
    private String sexo;
    private String bairro;
    private String contacto;

    private Button remover;
    private Button info;
   
    private static ClienteDAO dao = new ClienteDAO();

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

  

    public Button getRemover() {
        return remover;
    }

    public void setRemover(Button remover) {
        this.remover = remover;
    }

    public Button getInfo() {
        return info;
    }

    public void setInfo(Button info) {
        this.info = info;
    }
     
    
    public void save(){
        
        if(dao.findById(idCliente) != null){
            dao.editar(this);
        }else{
            dao.inserir(this);
        }
    
    }
    
    public static List<Cliente> todos(){
        return dao.todos();
    }
    
    public static Cliente findById(int id){
        return dao.findById(id);
    }
    
    public void remover(){
        dao.delete(this);
    }
    public static List<Cliente> findByName(String n){
        return dao.findByName(n);
    }
      public static void main(String[] args) throws ParseException {
        Cliente f = new Cliente();
        
        f.setNome("fred");
        f.setSexo("Masculino");
        f.setData_nascimento(Help.date_from_string("12/07/1999"));
        f.setBairro("Mahotas");
        
        f.setContacto("84226677");
        f.save();
    }
    
}

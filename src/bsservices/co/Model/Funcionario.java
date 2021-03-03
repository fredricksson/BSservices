/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsservices.co.Model;


import bsservices.co.Dao.FuncionarioDAO;
import bsservices.co.Factory.Help;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Button;

/**
 *
 * @author doroteia
 */
public class Funcionario {

    private int id;
    private String nome;
    private String nr_bi;
    private Date data_nascimento;
    private String contacto;
    private String bairro;
    private String sexo;
    private String email;
    private Button remover;
    private Button info;
   
    
    private static FuncionarioDAO dao = new FuncionarioDAO();
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNr_bi() {
        return nr_bi;
    }

    public void setNr_bi(String nr_bi) {
        this.nr_bi = nr_bi;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        
        if(dao.findById(id) != null){
            dao.editar(this);
        }else{
            dao.inserir(this);
        }
    
    }
     
     
     
     
     public static List<Funcionario> todos(){
        return dao.todos();
    }
    
    public static Funcionario findById(int id){
        return dao.findById(id);
    }
    
    public void remover(){
        dao.delete(this);
    }
    public static List<Funcionario> findByName(String n){
        return dao.findByName(n);
    }
    
  
}

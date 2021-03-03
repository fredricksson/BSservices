/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsservices.co.Dao;

import bsservices.co.Factory.Conexao;
import bsservices.co.Model.Funcionario;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.JOptionPane;

/**
 *
 * @author doroteia
 */
public class FuncionarioDAO extends Conexao {
    
     
    
    
    
    public void inserir(Funcionario func){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO funcionario ");
        sql.append("SET nome = ?, data_nascimento = ?, ");
        sql.append("sexo = ?, bairro = ?, ");
        sql.append("contacto = ? , nr_bi = ? ");
        open();
    
        try{
              PreparedStatement ps = conexao.prepareStatement(sql.toString());
              ps.setString(1, func.getNome());
              ps.setDate(2, (Date) func.getData_nascimento());
              ps.setString(3, func.getSexo());
              ps.setString(4, func.getBairro());
              ps.setString(5, func.getContacto());
              ps.setString(6, func.getNr_bi());
              ps.executeUpdate();
              ps.close();
        }catch(SQLException e){
            System.out.println(e.getCause());
          //  JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
          close();
        }
        
    }
    
    public void delete(Funcionario func){
        String sql = "DELETE FROM `funcionario` WHERE idFuncionario = ?";
        open();
           try{
              PreparedStatement ps = conexao.prepareStatement(sql);
              ps.setInt(1, func.getId());
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro de conexao");
        }finally{
         close();
        }
    }
    
    public void editar(Funcionario func){
        
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE  funcionario ");
        sql.append("SET nome = ?, data_nascimento = ?, ");
        sql.append("sexo = ?, bairro = ?, ");
        sql.append("contacto = ? , nr_bi = ? ");
        sql.append("WHERE `id` = ?");
        open();
           try{
              PreparedStatement ps = conexao.prepareStatement(sql.toString());
              ps.setString(1, func.getNome());
              ps.setDate(2, (Date) func.getData_nascimento());
              ps.setString(3, func.getSexo());
              ps.setString(4, func.getBairro());
              ps.setString(5, func.getContacto());
              ps.setString(6, func.getNr_bi());
              ps.setInt(7, func.getId());
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro de conexao");
        }finally{
          close(); 
        }
    }
    
    
    public List<Funcionario> todos(){
        ArrayList<Funcionario> listaFuncionario = new ArrayList<>();
        open();
        try {
            ResultSet rs;
            PreparedStatement stm = conexao.prepareStatement("SELECT * FROM funcionario");
            rs = stm.executeQuery();
            while (rs.next()) {
                Funcionario f = new Funcionario();
                Button b = new Button();
                ImageView im = new ImageView(new Image("/bsservices/co/icons/info.png"));
                im.setFitHeight(30);
                im.setFitWidth(30);
                b.setStyle("-fx-background-color: transparent");
                b.setGraphic(im);
                f.setInfo(b);
                
                ImageView im2 = new ImageView(new Image("/bsservices/co/icons/icons8-delete-50.png"));
                
                Button b2 = new Button();
                im2.setFitHeight(30);
                im2.setFitWidth(30);
                b2.setStyle("-fx-background-color: transparent");
                b2.setGraphic(im2);
                f.setRemover(b2);
                
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setData_nascimento(rs.getDate("data_nascimento"));
                f.setBairro(rs.getString("bairro"));
                f.setContacto(rs.getString("contacto"));
                f.setSexo(rs.getString("sexo"));
                listaFuncionario.add(f);
            }
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(null, "Erro de conexao");
        } finally {
           close();
        }
        return listaFuncionario;
    }
    
    public Funcionario findById(int id){
         Funcionario f = null;
         open();
        try {
            ResultSet rs;
            PreparedStatement stm = conexao.prepareStatement("SELECT * FROM funcionario where id= ?");
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
               
                f.setId(rs.getInt("idFuncionario"));
                f.setNome(rs.getString("nome"));
                f.setData_nascimento(rs.getDate("data_nascimento"));
                f.setBairro(rs.getString("bairro"));
                f.setContacto(rs.getString("contacto"));
          
                f.setSexo(rs.getString("sexo"));
                
            }
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(null, "Erro de conexao");
        } finally {
          close();
        }
        return f;
    }
    
    public List<Funcionario> findByName(String name) {
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
        open();
        try {
            ResultSet rs;
            PreparedStatement stm = conexao.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ?");
            stm.setString(1, "%"+name+"%");
            rs = stm.executeQuery();
            while (rs.next()) {
                Funcionario f = new Funcionario();
                Button b = new Button();
                ImageView im = new ImageView(new Image("/bsservices/co/icons/info.png"));
                
                im.setFitHeight(30);
                im.setFitWidth(30);
                b.setStyle("-fx-background-color: transparent");
                b.setGraphic(im);
                f.setInfo(b);
                
                ImageView im2 = new ImageView(new Image("/bsservices/co/icons/icons8-delete-50.png"));
                
                Button b2 = new Button();
                im2.setFitHeight(30);
                im2.setFitWidth(30);
                b2.setStyle("-fx-background-color: transparent");
                b2.setGraphic(im2);
                f.setRemover(b2);
                
                
                f.setId(rs.getInt("idFuncionario"));
                f.setNome(rs.getString("nome"));
                f.setData_nascimento(rs.getDate("data_nascimento"));
                f.setBairro(rs.getString("bairro"));
                f.setContacto(rs.getString("contacto"));
              
                f.setSexo(rs.getString("sexo"));
                listaFuncionarios.add(f);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as cidades: " + e.getMessage());
        } finally {
            close();
        }
        return listaFuncionarios;
    }
    
}

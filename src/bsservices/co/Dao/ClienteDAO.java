/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsservices.co.Dao;

import bsservices.co.Factory.Conexao;
import bsservices.co.Factory.Help;
import bsservices.co.Model.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
public class ClienteDAO extends Conexao {
   
    
    public void inserir(Cliente cliente){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO cliente ");
        sql.append("SET nome = ?, data_nascimento = ?, ");
        sql.append("sexo = ?, bairro = ?, ");
        sql.append("contacto = ?  ");
        open();
        try{
              PreparedStatement ps = conexao.prepareStatement(sql.toString());
              ps.setString(1, cliente.getNome());
              ps.setDate(2, (Date) cliente.getData_nascimento());
              ps.setString(3, cliente.getSexo());
              ps.setString(4, cliente.getBairro());
              ps.setString(5, cliente.getContacto());
              
              ps.executeUpdate();
              ps.close();
        }catch(SQLException e){
            System.out.println( e.getMessage());
          //  JOptionPane.showMessageDialog(null,);
        }finally{
            close();
          
            }
        }
        
    
    
    public void delete(Cliente cliente){
        String sql = "DELETE FROM `cliente` WHERE idCliente = ?";
        
           try{
              PreparedStatement ps = conexao.prepareStatement(sql);
              ps.setInt(1, cliente.getIdCliente());
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro de conexao");
        }finally{
            try {
                conexao.close();
            } catch (SQLException ex) {
            }
        }
    }
    
    public void editar(Cliente cliente){
        
         StringBuilder sql = new StringBuilder();
        sql.append("UPDATE  cliente ");
        sql.append("SET nome = ?, data_nascimento = ?, ");
        sql.append("sexo = ?, bairro = ?, ");
        sql.append("contacto = ? ");
        sql.append("WHERE `idcliente` = ?");
        open();
           try{
              PreparedStatement ps = conexao.prepareStatement(sql.toString());
               System.out.println("sim");
              ps.setString(1, cliente.getNome());
              ps.setDate(2, (Date) cliente.getData_nascimento());
              ps.setString(3, cliente.getSexo());
              ps.setString(4, cliente.getBairro());
              ps.setString(5, cliente.getContacto());
             
              ps.setInt(6, cliente.getIdCliente());
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro de conexao");
        }finally{
            
            
            close();
        }
    }
    
    
    public List<Cliente> todos(){
        ArrayList<Cliente> listaCliente = new ArrayList<>();
        open();
        try {
            ResultSet rs;
            PreparedStatement stm = conexao.prepareStatement("SELECT * FROM cliente");
            rs = stm.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                Button b = new Button();
                ImageView im = new ImageView(new Image("/bsservices/co/icons/MarkZ.jpg",50,50,false,false));
                
                //im.setFitHeight(50);
               // im.setFitWidth(50);
            //    b.setStyle("-fx-background-color: transparent");
                b.setGraphic(im);
                c.setInfo(b);
                
                ImageView im2 = new ImageView(new Image("/bsservices/co/icons/icons8-delete-50.png"));
                
                Button b2 = new Button();
                im2.setFitHeight(30);
                im2.setFitWidth(30);
                b2.setStyle("-fx-background-color: transparent");
                b2.setGraphic(im2);
                c.setRemover(b2);
                
                
                c.setIdCliente(rs.getInt("idCliente"));
                c.setNome(rs.getString("nome"));
                c.setData_nascimento(rs.getDate("data_nascimento"));
                c.setBairro(rs.getString("bairro"));
                c.setContacto(rs.getString("contacto"));
              
                c.setSexo(rs.getString("sexo"));
                listaCliente.add(c);
            }
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(null,e.getStackTrace());
        } finally {
            close();
        }
        return listaCliente;
    }
    
    public Cliente findById(int id){
         Cliente c = null;
         open();
        try {
            ResultSet rs;
            PreparedStatement stm = conexao.prepareStatement("SELECT * FROM cliente where idCliente = ?");
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
               
                c.setIdCliente(rs.getInt("idCliente"));
                c.setNome(rs.getString("nome"));
                c.setData_nascimento(rs.getDate("data_nascimento"));
                c.setBairro(rs.getString("bairro"));
                c.setContacto(rs.getString("contacto"));
      
                c.setSexo(rs.getString("sexo"));
                
            }
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(null, "Erro de conexao");
        } finally {
          close();
        }
        return c;
    }
    
     public List<Cliente> findByName(String name) {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        open();
        try {
            ResultSet rs;
            PreparedStatement stm = conexao.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ?");
            stm.setString(1, "%"+name+"%");
            rs = stm.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                Button b = new Button();
                ImageView im = new ImageView(new Image("/bsservices/co/icons/info.png"));
                
                im.setFitHeight(30);
                im.setFitWidth(30);
                b.setStyle("-fx-background-color: transparent");
                b.setGraphic(im);
                c.setInfo(b);
                
                ImageView im2 = new ImageView(new Image("/bsservices/co/icons/icons8-delete-50.png"));
                
                Button b2 = new Button();
                im2.setFitHeight(30);
                im2.setFitWidth(30);
                b2.setStyle("-fx-background-color: transparent");
                b2.setGraphic(im2);
                c.setRemover(b2);
                
                
                c.setIdCliente(rs.getInt("idCliente"));
                c.setNome(rs.getString("nome"));
                c.setData_nascimento(rs.getDate("data_nascimento"));
                c.setBairro(rs.getString("bairro"));
                c.setContacto(rs.getString("contacto"));
              
                c.setSexo(rs.getString("sexo"));
                listaClientes.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as cidades: " + e.getMessage());
        } finally {
            close();
        }
        return listaClientes;
    }
    
  
}

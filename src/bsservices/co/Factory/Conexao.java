/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsservices.co.Factory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;

public class Conexao    {


  private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE = "bsservices";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    protected Connection conexao;
    protected Statement stm;

    public void open() {
        conexao = null;
        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL + DATABASE, USERNAME, PASSWORD);
            stm = conexao.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e);
        }
        System.out.println("Connected");
    }

    public void close() {
        try {
            if (conexao != null)
                conexao.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão com o banco:" + e);
        }
    }
    
    
   
}
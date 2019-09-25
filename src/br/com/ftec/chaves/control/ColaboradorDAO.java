/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ftec.chaves.control;

import br.com.ftec.chaves.model.Colaborador;
import br.com.ftec.chaves.model.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADM
 */
public class ColaboradorDAO {

    public void salvar(Colaborador colaborador) throws Exception {
        String sql = "INSERT INTO colaborador (nome, cpf, senha, telefone, email) "
                + "VALUES (?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;
        try { //se existir sucesso na conexão
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            
            //cada pstm.set coloca valor em uma das "?" na ordem
            pstm.setString(1, colaborador.getNome());
            pstm.setString(2, colaborador.getCpf());
            pstm.setString(3, colaborador.getSenha());
            pstm.setString(4, colaborador.getTelefone());
            pstm.setString(5, colaborador.getEmail());
            pstm.execute(); //executa o SQL.

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public Colaborador buscaColaboradorCpf(String cpf) throws Exception{//buscar o CPF por parâmetro
        
        String sql = "SELECT * FROM COLABORADOR "
                + "WHERE CPF = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        //classe que vai recuperar os dados do Banco dds
        ResultSet rset = null;
        
        Colaborador colaborador = new Colaborador();//objeto
        
        conn = ConnectionFactory.createConnectionToMySQL();
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, cpf);
        String a = "SELECT * FROM COLABORADOR WHERE CPF = '666'";
        rset = pstm.executeQuery();
        
        while(rset.next()){//enquanto tiver, vamos fazer
            colaborador.setId(rset.getInt("id"));
            colaborador.setEmail(rset.getString("email"));
            colaborador.setNome(rset.getString("nome"));
            colaborador.setSenha(rset.getString("senha"));
            colaborador.setTelefone(rset.getString("telefone"));
            colaborador.setCpf(rset.getString("cpf"));  
        }
        return colaborador;
    }
public List<Colaborador> listaColaboradores() throws SQLException, Exception{
        
        
        String  sqli = "SELECT * FROM Colaborador";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null; 
        
        ArrayList<Colaborador> listaColaboradores = new ArrayList<Colaborador>();
        
        conn = ConnectionFactory.createConnectionToMySQL();
        pstm = conn.prepareStatement(sqli);
        rset = pstm.executeQuery();
        
        while(rset.next()){
            Colaborador colaborador = new Colaborador();
            
            colaborador.setId(rset.getInt("id"));
            colaborador.setEmail(rset.getString("email"));
            colaborador.setNome(rset.getString("nome"));
            colaborador.setSenha(rset.getString("senha"));
            colaborador.setTelefone(rset.getString("telefone"));
            colaborador.setCpf(rset.getString("cpf"));  
            listaColaboradores.add(colaborador);
        }
        
        return listaColaboradores;
    }
}

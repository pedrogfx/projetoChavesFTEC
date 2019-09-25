/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ftec.chaves.control;

import br.com.ftec.chaves.model.Colaborador;
import br.com.ftec.chaves.model.Reserva;
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
public class ReservaDAO {
    
    public void salvar(Reserva reserva) throws Exception {
        String sql = "INSERT INTO reserva( id_sala, id_colaborador, turno, dia)"
                + " VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;
        try { //se existir sucesso na conexão
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            //cada pstm.set coloca valor em uma das "?" na ordem
            pstm.setInt(1, reserva.getSala().getId());
            pstm.setInt(2, reserva.getColaborador().getId());
            pstm.setString(3, reserva.getTurno());
            pstm.setString(4, reserva.getDia());
            pstm.execute(); //executa o SQL.
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Reserva> listaReservas() throws SQLException, Exception{
        
        
        String  sqli = "SELECT * FROM Reserva";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null; 
        
        ArrayList<Reserva> listaReservas = new ArrayList<Reserva>();
        
        conn = ConnectionFactory.createConnectionToMySQL();
        pstm = conn.prepareStatement(sqli);
        rset = pstm.executeQuery();
        
        while(rset.next()){
            Reserva reserva = new Reserva();
            Colaborador c = new Colaborador();
            Sala s = new Sala();
            c.setId(rset.getInt("id_colaborador"));
            reserva.setColaborador(c);
            s.setId(rset.getInt("id_sala"));
            reserva.setSala(s);
            reserva.setDia(rset.getString("dia"));
            reserva.setTurno(rset.getString("turno"));
               
            listaReservas.add(reserva);
        }
        
        return listaReservas;
    }
}

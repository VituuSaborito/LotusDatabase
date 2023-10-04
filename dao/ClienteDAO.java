package dao;

import factory.ConnectionFactory;
import modelo.Usuario;

import java.sql.*;
import java.sql.PreparedStatement;

public class ClienteDAO {
	private Connection connection;
	
	public ClienteDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Usuario user) {
		String sql  = "INSERT INTO Usuario(usuario_nome, usuario_email, usuario_senha) VALUES (?, ?, ?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getNome());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getSenha());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException u){
				throw new RuntimeException(u);
		}
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.sql.SQLException;

import factory.ConnectionFactory;
import modelo.Arquivos;
import modelo.Usuario;

public class ClienteDAO {
	private static Connection connection;
	static ArrayList<Arquivos>lista;
	
	public ClienteDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Usuario user) {
		String sql  = "INSERT INTO usuario(usuario_nome, usuario_email, usuario_senha) VALUES (?, ?, ?)";
		
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
	
	public void adiciona(Arquivos file) {
		String sql = "INSERT INTO arquivos(arquivo_nome, arquivo_caminho, arquivo_formato) VALUES (?, ?, ?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, file.getNome());
			stmt.setString(1, file.getCaminho());
			stmt.setString(1, file.getFormato());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
	}
	
	public static boolean login(Usuario user) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE usuario_nome = ? AND usuario_email = ? AND usuario_senha = ?";
												//ideal que usasse OR aqui para entrar com o nome ou email
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, user.getNome());
		stmt.setString(2, user.getEmail());
		stmt.setString(3, user.getSenha());
		stmt.execute();

		ResultSet resultSet = stmt.getResultSet();

		return resultSet.next();


	}
	
	public static ArrayList<Arquivos> listarArquivos() throws SQLException{
		ArrayList<Arquivos> listaArquivos = new ArrayList<>();
		Connection connection = ConnectionFactory.getConnection();
		
		if(connection!= null) {
			PreparedStatement consultaSQL = null;
			ResultSet resultadoSQL = null;
			
			
			String sql = "select * from arquivos";
			try {
				consultaSQL = connection.prepareStatement(sql);
				resultadoSQL = consultaSQL.executeQuery(); 	
				
				while(resultadoSQL.next()) {
					Arquivos arquivos = new Arquivos();
					
					arquivos.setNome(resultadoSQL.getString("arquivo_nome"));
					arquivos.setCaminho(resultadoSQL.getString("arquivo_caminho"));
					arquivos.setFormato(resultadoSQL.getString("arquivo_formato"));
					
					listaArquivos.add(arquivos);
				}
				
			} catch(SQLException e) {
				
			}finally {
				connection.close();
			}
		}
		return listaArquivos;
		
	}
	
	public void exclui(Arquivos file) {
		String sql = "DELETE FROM arquivos WHERE arquivo_nome = ? AND arquivo_caminho = ? AND arquivo_formato = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, file.getNome());
			stmt.setString(1, file.getCaminho());
			stmt.setString(1, file.getFormato());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
	}
	
	public void alterar(Arquivos file) {
		String sql = "UPDATE arquivos(arquivo_nome) SET arquivo_nome = ? WHERE arquivo_caminho = ? AND arquivo_formato = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, file.getNome());
			stmt.setString(1, file.getCaminho());
			stmt.setString(1, file.getFormato());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
	}
	
	
}

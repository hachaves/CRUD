package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Carrinho;

/**
 * Classe do suporte para utiliza��o no banco de dados
 */
public class SuporteDAO {

	/**
	 * Fun��o que retorna uma lista com todas as compras em um carrinho no banco de dados
	 * 
	 * @return List
	 * @throws DAOException exce��o DAO
	 */
	public List<Carrinho> pesquisarTodasCompras() throws DAOException{
		List<Carrinho> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "SELECT * FROM carrinho";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Carrinho c = new Carrinho();
				c.setClienteEmail(rs.getString("cliente_logon_email"));
				c.setIdProduto(rs.getInt("produto_id"));
				lista.add(c);
			}
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		return lista;
	}

	/**
	 * Fun��o que retorna um carrinho vinculado a um email no banco de dados
	 * @param email String
	 * @return List
	 * @throws DAOException exce��o DAO
	 */
	public List<Carrinho> pesquisarPorEmail(String email) throws DAOException {
		List<Carrinho> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "SELECT * FROM carrinho WHERE cliente_logon_email LIKE ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + email + "%");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Carrinho c = new Carrinho();
				c.setClienteEmail(rs.getString("cliente_logon_email"));
				c.setIdProduto(rs.getInt("id"));
				lista.add(c);
			}
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		return lista;
	}

}

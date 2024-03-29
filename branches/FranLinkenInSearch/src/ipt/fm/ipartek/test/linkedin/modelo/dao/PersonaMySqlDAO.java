package ipt.fm.ipartek.test.linkedin.modelo.dao;

import ipt.fm.ipartek.test.linkedin.bean.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonaMySqlDAO implements IPersonaDAO {
	private final String tablaPersona = "persona";
	private final String columnaId = "id";
	private final String columnaNombre = "nombre";
	private final String columnApellidos = "apellido1";
	private final String columnaFoto = "foto";

	@Override
	public ArrayList<Persona> getAll() {
		ArrayList<Persona> personas = new ArrayList<Persona>();

		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conexion = MySqlDAOFactory.getInstance().conectar();

			// consultar tabla personas
			st = conexion.createStatement();
			// Recoger resultados
			rs = st.executeQuery("select * from " + tablaPersona);
			Persona p = null;
			while (rs.next()) {
				p = new Persona(rs.getString(columnaNombre),
						rs.getString(columnApellidos), rs.getInt(columnaId));
				p.setFoto(rs.getString(columnaFoto));
				personas.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			personas = null;
		} finally { // cerrar todos los objetos creados para el acceso a BBDD
			cerrarObjetos(st, rs);
			MySqlDAOFactory.getInstance().desconectar();
		}

		return personas;
	}

	@Override
	public Persona getById(Persona p) {
		Persona persona = null;

		Connection conexion = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conexion = MySqlDAOFactory.getInstance().conectar();

			String sqlInsert = "select * from " + columnaNombre + " where "
					+ columnaId + " = ?";
			st = conexion.prepareStatement(sqlInsert);
			st.setInt(1, p.getId());
			rs = st.executeQuery();
			if (rs.first()) {
				persona = new Persona(rs.getString(columnaNombre),
						rs.getString(columnApellidos), rs.getInt(columnaId));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // cerrar todos los objetos creados para el acceso a BBDD
			cerrarObjetos(st, rs);
			MySqlDAOFactory.getInstance().desconectar();
		}

		return persona;
	}

	@Override
	public Persona insert(Persona p) {
		Persona persona = null;

		Connection conexion = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;

		try {
			conexion = MySqlDAOFactory.getInstance().conectar();

			// insertar persona nueva
			String sqlInsert = "INSERTz INTO " + tablaPersona + "( "
					+ columnaNombre + ", " + columnApellidos + ", "
					+ columnaFoto + ") VALUES (?,?,?)";
			st2 = conexion.prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			st2.setString(1, p.getNombre());
			st2.setString(2, p.getApellidos());
			st2.setString(3, p.getFoto());
			st2.executeUpdate();

			rs = st2.getGeneratedKeys();
			int i = -1;
			if (rs != null && rs.next()) {
				i = rs.getInt(1);
			}
			p.setId(i);
			persona = p;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cerrarObjetos(st2, rs);
			MySqlDAOFactory.getInstance().desconectar();
		}
		return persona;
	}

	@Override
	public boolean delete(Persona p) {
		boolean resultado = false;

		Connection conexion = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;

		try {
			conexion = MySqlDAOFactory.getInstance().conectar();

			String sqlInsert = "DELETE FROM " + tablaPersona + " WHERE "
					+ columnaId + "=?";
			st2 = conexion.prepareStatement(sqlInsert);
			st2.setInt(1, p.getId());
			st2.executeUpdate();

			resultado = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cerrarObjetos(st2, rs);
			MySqlDAOFactory.getInstance().desconectar();
		}
		return resultado;
	}

	@Override
	public Persona update(Persona p) {
		Persona persona = null;

		Connection conexion = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;

		try {
			conexion = MySqlDAOFactory.getInstance().conectar();

			String sqlInsert = "UPDATE " + tablaPersona + " SET "
					+ columnaNombre + "=?, " + columnApellidos + "=? WHERE "
					+ columnaId + "=?";
			st2 = conexion.prepareStatement(sqlInsert);
			st2.setString(1, p.getNombre());
			st2.setString(2, p.getApellidos());
			st2.setInt(3, p.getId());
			st2.executeUpdate();

			persona = new Persona(p.getNombre(), p.getApellidos(), p.getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cerrarObjetos(st2, rs);
			MySqlDAOFactory.getInstance().desconectar();
		}
		return persona;
	}

	private void cerrarObjetos(Statement st, ResultSet rs) {
		// cerrar ResultSet
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// cerrar Statements
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

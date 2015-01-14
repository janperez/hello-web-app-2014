package ipt.fm.ipartek.test.linkedin.controller;

import ipt.fm.ipartek.test.linkedin.FactoriaMySql;
import ipt.fm.ipartek.test.linkedin.bean.Persona;
import ipt.fm.ipartek.test.linkedin.modelo.dao.DAOFactory;
import ipt.fm.ipartek.test.linkedin.modelo.dao.IPersonaDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PersonaServlet
 */
public class PersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IPersonaDAO daoPersona = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DAOFactory factoria = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		daoPersona = factoria.getPersonaDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		if (id == null) {

		ArrayList<Persona> vPersonas = daoPersona.getAll();
		}
		// conectar BBDD
		// String resultado = consultarPersonas();

		// pasar attributo resultado
		request.setAttribute("personas", vPersonas);
		// forward a jsp de busqueda
		request.getRequestDispatcher("view_personas.jsp").forward(request,
				response);


		// Miramos que accion ejecutamos
		// if ("Crear".equalsIgnoreCase(accion)) {
		// persona.setId(totalRegistros());
		// insertar(persona);
		// } else {
		// if ("Buscar".equalsIgnoreCase(accion)) {
		// mostrarConsulta(consultar(persona));
		// }
		// }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Persona persona = null;
		// Recogemos la accion a ejecutar
		String accion = request.getParameter("action");

		// Miramos que accion ejecutamos
		if ("crear".equalsIgnoreCase(accion)) {
			// Recogemos los datos de la Persona
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String foto = request.getParameter("foto");
			persona = new Persona(nombre, apellidos);
			persona.setFoto(foto);
			daoPersona.insert(persona);
		} else {
			String txtId = request.getParameter("id");
			int id = Integer.parseInt(request.getParameter("id"));
			persona = new Persona();
			persona.setId(id);
			persona = daoPersona.getById(persona);
			if ("modificar".equalsIgnoreCase(accion)) {
				request.setAttribute("id", id);
			} else {
				// borrar
			}
		}
		doGet(request, response);
	}

	/**
	 * Inserta una Persona en la tabla
	 * 
	 * @param persona
	 *            Persona que se va insertar en la tabla
	 * @return true si se ha insertado correctamente, false eoc
	 */
	private boolean insertar(Persona persona) {
		boolean bInsertado = false;

		String sqlInsert = "INSERT INTO persona ( id, nombre, apellidos, edad) VALUES (?, ?, ? ,? );";
		try {
			Connection conexion = FactoriaMySql.conectar();
			PreparedStatement st = conexion.prepareStatement(sqlInsert);
			// sustituir ? por valores en la sentencia SQL del prepareStatement
			st.setInt(1, persona.getId());
			st.setString(2, persona.getNombre());
			st.setString(3, persona.getApellidos());
			st.setInt(4, 69);

			// 4.- Ejecutar la sentencia
			st.executeUpdate();

			bInsertado = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bInsertado;

	}

	/**
	 * Modifica una Persona de la tabla
	 * 
	 * @param persona
	 *            Persona a modificar
	 * @return true si se ha modificado alguien, false eoc
	 */
	private boolean modificar(Persona persona) {
		boolean bModificado = false;
		int nModificados = 0;

		String sqlUpdate = "UPDATE personas SET nombre=?, apellidos=? where id=?;";
		try {
			Connection conexion = FactoriaMySql.conectar();
			PreparedStatement st = conexion.prepareStatement(sqlUpdate);
			// sustituir ? por valores en la sentencia SQL del prepareStatement
			st.setString(1, persona.getNombre());
			st.setString(2, persona.getApellidos());
			st.setInt(3, persona.getId());

			// 4.- Ejecutar la sentencia
			nModificados = st.executeUpdate();
			bModificado = (nModificados == 0 ? false : true);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bModificado;
	}

	private int eliminar(String id) {
		int numEliminados = 0;

		String sqlDelete = "DELETE FROM personas WHERE id=?;";
		try {
			Connection conexion = FactoriaMySql.conectar();
			PreparedStatement st = conexion.prepareStatement(sqlDelete);
			// sustituir ? por valores en la sentencia SQL del prepareStatement
			st.setInt(1, Integer.parseInt(id));

			// 4.- Ejecutar la sentencia
			numEliminados = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return numEliminados;
	}

	private ResultSet consultar(Persona persona) {
		ResultSet rs = null;

		String sqlSelectAll = "SELECT * FROM personas;";
		String sqlSelect = "SELECT * FROM personas WHERE nombre=? OR apellidos=?;";
		Connection conexion = FactoriaMySql.conectar();
		PreparedStatement st;
		try {
			if (persona != null) {
				st = conexion.prepareStatement(sqlSelect);
				st.setString(1, persona.getNombre());
				st.setString(2, persona.getApellidos());
			} else {
				st = conexion.prepareStatement(sqlSelectAll);
			}
			rs = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	private int totalRegistros() {
		int numRegistros = -1;

		String sqlCount = "SELECT COUNT(*) AS total FROM personas;";
		Connection conexion = FactoriaMySql.conectar();

		try {
			PreparedStatement st = conexion.prepareStatement(sqlCount);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				numRegistros = Integer.parseInt(rs.getString("total"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return numRegistros;
	}

	private String mostrarConsulta(ResultSet rs) {
		String resultado = "";
		try {
			while (rs.next()) {
				resultado += rs.getString("nombre");
				resultado += rs.getString("apellidos");
				resultado += rs.getInt("edad");
				resultado += "<br>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

}

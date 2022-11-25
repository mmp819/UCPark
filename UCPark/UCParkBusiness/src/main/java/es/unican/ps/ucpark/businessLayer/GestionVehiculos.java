package es.unican.ps.ucpark.businessLayer;

import java.util.List;

import es.unican.ps.ucpark.daoLayer.IUsuariosDAO;
import es.unican.ps.ucpark.daoLayer.IVehiculosDAO;
import es.unican.ps.ucpark.domain.Usuario;
import es.unican.ps.ucpark.domain.Vehiculo;

public class GestionVehiculos implements IGestionVehiculos, IConsultaVehiculos {
	
	private IUsuariosDAO usuariosDAO;
	private IVehiculosDAO vehiculosDAO;
	
	
	public GestionVehiculos(IUsuariosDAO usuariosDAO) {
		usuariosDAO = this.usuariosDAO;
	}
	
	/**
	 * Registra un nuevo vehiculo a nombre de un usuario determinado.
	 * 
	 * @param vehiculo Vehiculo a registrar.
	 * @param idUsuario ID del usuario a quien asociar el vehiculo.
	 * 
	 * @return vehiculo registrado.
	 *         null si el usuario ya tenia ese vehiculo registrado.
	 */
	public Vehiculo registraVehiculo(Vehiculo vehiculo, int idUsuario) 
		throws OperacionNoValida {
		
		Usuario usuario = usuariosDAO.usuarioPorId(idUsuario);
		
		if (usuario == null) {
			throw new OperacionNoValida("No existe un usuario para el ID " +
					"indicado.");
		}
		
		Vehiculo vehiculoRegistrado = vehiculo;
		
		if (usuario.getVehiculos().contains(vehiculo)) {
			vehiculoRegistrado = null;
		} else {
			vehiculoRegistrado = vehiculosDAO.creaVehiculo(vehiculoRegistrado);
			
			usuario.getVehiculos().add(vehiculoRegistrado);
			usuariosDAO.modificaUsuario(usuario);
		}
		
		return vehiculoRegistrado;
	}
	
	/**
	 * Elimina un vehiculo registrado.
	 * 
	 * @param vehiculo Vehiculo a eliminar.
	 * 
	 * @return vehiculo eliminado.
	 */
	public Vehiculo eliminaVehiculo(Vehiculo vehiculo) {
		
		Usuario propietario = vehiculo.getPropietario();
		
		propietario.getVehiculos().remove(vehiculo);
		usuariosDAO.modificaUsuario(propietario);
		
		return vehiculosDAO.eliminaVehiculo(vehiculo);
	}
	
	/**
	 * Consulta vehiculos registrados a nombre de un usuario concreto.
	 * 
	 * @param idUsuario ID del usuario a consultar.
	 * @return lista de vehiculos registrados a nombre del usuario especificado.
	 *         null si no existen vehiculos registrados.
	 */
	public List<Vehiculo> consultaVehiculosRegistrados(int idUsuario) {
		
		Usuario propietario = usuariosDAO.usuarioPorId(idUsuario);
		
		if (propietario == null) {
			throw new OperacionNoValida("No existe un usuario con el ID" + 
					" especificado.");
		}
		
		List<Vehiculo> vehiculosRegistrados = propietario.getVehiculos();
		
		if (vehiculosRegistrados.isEmpty()) {
			vehiculosRegistrados = null;
		}
		
		return vehiculosRegistrados;
	}

}

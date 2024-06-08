package clases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.CSVReader;

public class Servicios {

	private Map<String, Tarea> tareasMap;

	/*
	 * Expresar la complejidad temporal del constructor.
	 */
	public Servicios(String pathProcesadores, String pathTareas) {

		this.tareasMap = new HashMap<>();
		/*
		 * Crear las estructuras de datos.
		 * Una para ids de tareas.
		 * Una para tareas criticas.
		 * Una para tareas no criticas.
		 */

		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas);
	}

	/*
	 * Expresar la complejidad temporal del servicio 1.
	 */
	public Tarea servicio1(String ID) {
		return tareasMap.get(ID);
	}

	/*
	 * Expresar la complejidad temporal del servicio 2.
	 */
	public List<Tarea> servicio2(boolean esCritica) {
		return null;
	}

	/*
	 * Expresar la complejidad temporal del servicio 3.
	 */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		return null;
	}

}
package clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import utils.CSVReader;

public class Servicios {

	private HashMap<String, Tarea> tareas;
	private TreeMap<Integer, List<String>> tareasCriticas;
	private TreeMap<Integer, List<String>> tareasNoCriticas;

	/*
	 * Expresar la complejidad temporal del constructor.
	 */
	public Servicios(String pathProcesadores, String pathTareas) {

		this.tareas = new HashMap<>();
		this.tareasCriticas = new TreeMap<>();
		this.tareasNoCriticas = new TreeMap<>();
		
		CSVReader reader = new CSVReader();
		this.tareas = reader.readTasks(pathTareas); 
		reader.readProcessors(pathProcesadores);
//Eliminar codigo repetido
		for(Tarea tarea : tareas.values()) {
			if(tarea.esCritica()) {
				if(tareasCriticas.get(tarea.getPrioridad()) == null) {
					// Agegar la lista con el id de la tarea a ese nodo del arbol
					LinkedList<String> listaDeFactoreo = new LinkedList<>();
					listaDeFactoreo.add(tarea.getId());
					tareasCriticas.put(tarea.getPrioridad(), listaDeFactoreo);
				} else {
					// Agregar el id de la tarea a la lista en el nodo del arbol
					tareasCriticas.get(tarea.getPrioridad()).add(tarea.getId());
				}
			} else {
				if(tareasNoCriticas.get(tarea.getPrioridad()) == null) {
					// Agegar la lista con el id de la tarea a ese nodo del arbol
					LinkedList<String> listaDeFactoreo = new LinkedList<>();
					listaDeFactoreo.add(tarea.getId());
					tareasNoCriticas.put(tarea.getPrioridad(), listaDeFactoreo);
				} else {
					// Agregar el id de la tarea a la lista en el nodo del arbol
					tareasNoCriticas.get(tarea.getPrioridad()).add(tarea.getId());
				}
			}
		}
	}

	/*
	 * Expresar la complejidad temporal del servicio 1.
	 */
	public Tarea servicio1(String ID) {
		return tareas.get(ID);
	}

	/*
	 * Expresar la complejidad temporal del servicio 2.
	 */
	public List<Tarea> servicio2(boolean esCritica) {
//Eliminar codigo repetido		
		if(esCritica) {
			ArrayList<Tarea> listaDeCriticas = new ArrayList<>();
			for (List<String> tareasConCiertaPrioridad : this.tareasCriticas.values()) {
				for(String id : tareasConCiertaPrioridad) {
					listaDeCriticas.add(servicio1(id));
				}
			}
			return listaDeCriticas;
		} else {
			ArrayList<Tarea> listaDeNoCriticas = new ArrayList<>();
			for (List<String> tareasConCiertaPrioridad : this.tareasCriticas.values()) {
				for(String id : tareasConCiertaPrioridad) {
					listaDeNoCriticas.add(servicio1(id));
				}
			}
			return listaDeNoCriticas;
		}
	}

	/*
	 * Expresar la complejidad temporal del servicio 3.
	 */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
//Eliminar codigo repetido		
		ArrayList<Tarea> tareasEnElRango = new ArrayList<>();
		for (List<String> tareasConCiertaPrioridad : this.tareasCriticas.subMap(prioridadInferior, 
															true, 
															prioridadSuperior, 
															true).values()) {
			for(String id : tareasConCiertaPrioridad) {
				tareasEnElRango.add(servicio1(id));
			}
		}
		for (List<String> tareasConCiertaPrioridad : this.tareasNoCriticas.subMap(prioridadInferior, 
															true, 
															prioridadSuperior, 
															true).values()) {
			for(String id : tareasConCiertaPrioridad) {
				tareasEnElRango.add(servicio1(id));
			}
		}
		return tareasEnElRango;
	}

}
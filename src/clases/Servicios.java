package clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import utils.CSVReader;

public class Servicios {

	// HashMap que guarda las tareas con clave Id.
	private HashMap<String, Tarea> tareas;
	// Árboles Rojo-Negros que guardan id asociado a la clave prioridad.
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

		for(Tarea tarea : tareas.values()) {
			if(tarea.esCritica()) {
				this.agregarIdTarea(tareasCriticas, tarea);
			} else {
				this.agregarIdTarea(tareasNoCriticas, tarea);
			}
		}
	}

	/*
	 * Agregar un id de una tarea a uno de los árboles.
	*/
	private void agregarIdTarea(TreeMap<Integer, List<String>> mapa, Tarea tarea) {
		// Si el nodo que corresponde no tiene ningún id guardado.
		if(mapa.get(tarea.getPrioridad()) == null) {
			// Agegar una lista de factoreo con el id de la tarea a ese nodo del árbol.
			LinkedList<String> listaDeFactoreo = new LinkedList<>();
			listaDeFactoreo.add(tarea.getId());
			mapa.put(tarea.getPrioridad(), listaDeFactoreo);
		} else {
			// Agregar el id de la tarea a la lista almacenada en el nodo en cuestion.
			mapa.get(tarea.getPrioridad()).add(tarea.getId());
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

		if(esCritica) {
			ArrayList<Tarea> listaDeCriticas = this.getTareasFrom(this.tareasCriticas);
			return listaDeCriticas;
		} else {
			ArrayList<Tarea> listaDeNoCriticas = this.getTareasFrom(this.tareasNoCriticas);
			return listaDeNoCriticas;
		}
	}

	/*
	 * Expresar la complejidad temporal del servicio 3.
	 */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
	
		ArrayList<Tarea> resultado = new ArrayList<>(); 
		resultado = this.getTareasFrom(this.tareasCriticas.subMap(prioridadInferior, 
																true, 
																prioridadSuperior, 
																true));
		resultado.addAll(this.getTareasFrom(this.tareasNoCriticas.subMap(prioridadInferior, 
																		true, 
																		prioridadSuperior, 
																		true)));					
		return resultado;
	}

	private ArrayList<Tarea> getTareasFrom(NavigableMap<Integer,List<String>> mapa) {
		ArrayList<Tarea> resultado = new ArrayList<>();
		for (List<String> tareasConCiertaPrioridad : mapa.values()) {
			for(String id : tareasConCiertaPrioridad) {
				resultado.add(servicio1(id));
			}
		}
		return resultado;
	}

}
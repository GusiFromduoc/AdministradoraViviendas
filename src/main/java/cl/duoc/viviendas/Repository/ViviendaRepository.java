package cl.duoc.viviendas.Repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import cl.duoc.viviendas.DAO.ViviendaDAO;
import cl.duoc.viviendas.model.Casa;
import cl.duoc.viviendas.model.Departamento;
import cl.duoc.viviendas.model.Vivienda;

import java.util.List;

public class ViviendaRepository {
    private final ViviendaDAO dao;
    private final ObservableList<Vivienda> listaViviendas;

    public ViviendaRepository() {
        this.dao = new ViviendaDAO();
        this.listaViviendas = FXCollections.observableArrayList();
        cargarDatos();
    }

    public ObservableList<Vivienda> getListaViviendas() {
        return listaViviendas;
    }

    public void cargarDatos() {
        listaViviendas.clear();
        List<Casa> casas = dao.cargarCasas();
        List<Departamento> depto = dao.cargarDepartamentos();
        
        listaViviendas.addAll(casas);
        listaViviendas.addAll(depto);
    }

    public void agregarVivienda(Vivienda vivienda) {
        listaViviendas.add(vivienda);
        sincronizarPersistencia();
    }

    public void eliminarVivienda(Vivienda vivienda) {
        listaViviendas.remove(vivienda);
        sincronizarPersistencia();
    }

    private void sincronizarPersistencia() {
        List<Casa> casas = new java.util.ArrayList<>();
        List<Departamento> departamentos = new java.util.ArrayList<>();

        for (Vivienda v : listaViviendas) {
            if (v instanceof Casa) {
                casas.add((Casa) v);
            } else if (v instanceof Departamento) {
                departamentos.add((Departamento) v);
            }
        }

        dao.guardarCasas(casas);
        dao.guardarDepartamentos(departamentos);
    }
}
package cl.duoc.viviendas.DAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import cl.duoc.viviendas.model.Casa;
import cl.duoc.viviendas.model.Departamento;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ViviendaDAO {
    private static final String ARCHIVO_CASAS = "casas.json";
    private static final String ARCHIVO_DEPARTAMENTOS = "departamentos.json";
    private final Gson gson;

    public ViviendaDAO() {
        // Gson configurado con formato legible (opcional pero ayuda a depurar)
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // --- GUARDAR Y CARGAR CASAS ---
    public void guardarCasas(List<Casa> casas) {
        try (FileWriter writer = new FileWriter(ARCHIVO_CASAS)) {
            gson.toJson(casas, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar casas: " + e.getMessage());
        }
    }

    public List<Casa> cargarCasas() {
        List<Casa> casas = new ArrayList<>();
        try (FileReader reader = new FileReader(ARCHIVO_CASAS)) {
            Type tipoLista = new TypeToken<ArrayList<Casa>>() {}.getType();
            List<Casa> cargadas = gson.fromJson(reader, tipoLista);
            if (cargadas != null) {
                casas.addAll(cargadas);
            }
        } catch (IOException e) {
            // Si el archivo no existe la primera vez, retorna lista vacía sin error crítico
        }
        return casas;
    }

    // --- GUARDAR Y CARGAR DEPARTAMENTOS ---
    public void guardarDepartamentos(List<Departamento> departamentos) {
        try (FileWriter writer = new FileWriter(ARCHIVO_DEPARTAMENTOS)) {
            gson.toJson(departamentos, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar departamentos: " + e.getMessage());
        }
    }

    public List<Departamento> cargarDepartamentos() {
        List<Departamento> departamentos = new ArrayList<>();
        try (FileReader reader = new FileReader(ARCHIVO_DEPARTAMENTOS)) {
            Type tipoLista = new TypeToken<ArrayList<Departamento>>() {}.getType();
            List<Departamento> cargadas = gson.fromJson(reader, tipoLista);
            if (cargadas != null) {
                departamentos.addAll(cargadas);
            }
        } catch (IOException e) {
            // Si el archivo no existe la primera vez, retorna lista vacía
        }
        return departamentos;
    }
}
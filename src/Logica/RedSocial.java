package Logica;

import java.util.*;

public class RedSocial {
    private Map<String, Set<String>> grafo;

    public RedSocial() {
        grafo = new HashMap<>();
    }

    public void agregarUsuario(String usuario) {
        grafo.putIfAbsent(usuario, new HashSet<>());
    }

    public void agregarAmistad(String usuario1, String usuario2) {
        grafo.get(usuario1).add(usuario2);
        grafo.get(usuario2).add(usuario1);
    }

    public void eliminarAmistad(String usuario1, String usuario2) {
        grafo.get(usuario1).remove(usuario2);
        grafo.get(usuario2).remove(usuario1);
    }

    public void eliminarUsuario(String usuario) {
        grafo.remove(usuario);
        for (Set<String> amigos : grafo.values()) {
            amigos.remove(usuario);
        }
    }

    public Set<String> getUsuarios() {
        return new HashSet<>(grafo.keySet());
    }

    public List<String> busquedaAnchura(String inicio, String destino) {
        if (!grafo.containsKey(inicio) || !grafo.containsKey(destino)) {
            return null;
        }

        Queue<String> cola = new LinkedList<>();
        Map<String, String> padres = new HashMap<>();
        Set<String> visitados = new HashSet<>();

        cola.offer(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            String actual = cola.poll();
            if (actual.equals(destino)) {
                return reconstruirCamino(padres, inicio, destino);
            }

            for (String amigo : grafo.get(actual)) {
                if (!visitados.contains(amigo)) {
                    cola.offer(amigo);
                    visitados.add(amigo);
                    padres.put(amigo, actual);
                }
            }
        }

        return null;
    }

    public List<String> obtenerAmigosEnComun(String usuario1, String usuario2) {
        Set<String> amigos1 = grafo.get(usuario1);
        Set<String> amigos2 = grafo.get(usuario2);
        List<String> amigosEnComun = new ArrayList<>(amigos1);
        amigosEnComun.retainAll(amigos2);
        return amigosEnComun;
    }

    private List<String> reconstruirCamino(Map<String, String> padres, String inicio, String destino) {
        LinkedList<String> camino = new LinkedList<>();
        String actual = destino;
        while (actual != null) {
            camino.addFirst(actual);
            actual = padres.get(actual);
        }
        return camino;
    }
}
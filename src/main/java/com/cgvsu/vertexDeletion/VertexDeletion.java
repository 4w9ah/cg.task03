package com.cgvsu.vertexDeletion;

import com.cgvsu.model.*;

import java.util.ArrayList;

public class VertexDeletion {

    public static void deleteVertexes(Model model, ArrayList<Integer> deletedVertex) {
        if (deletedVertex.isEmpty()) return;
        if (model == null || model.vertices.isEmpty()) {
            throw new NullPointerException("Model is empty!");
        }
        ;
        //проходим по полигонам модели удаляя все,
        // которые содержат любую вершину из полученного "списка смерти"
        for (Polygon p : model.polygons) {
            for (Integer vertex : deletedVertex) {
                if (p.getVertexIndices().contains(vertex)) {
                    model.polygons.remove(p);
                }
            }
        }
        //Теперь помечаем к удалению "висячие" вершины
        int vertArrSize = model.vertices.size();
        ArrayList<Integer> vertToRemove = new ArrayList<>();
        for (int i = 0; i < vertArrSize; i++) {


            for (Polygon p : model.polygons) {
                if (p.getVertexIndices().contains(i)) {
                    vertToRemove.add(i);
                    break;
                }
            }
        }
        //Второй проход с правкой значений вертексов в полигонах
        for (int i = 0; i < vertToRemove.size(); i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (Polygon p : model.polygons) {
                tmp.clear();
                tmp = p.getVertexIndices();
                for (int j = 0; j < tmp.size(); j++) {
                    if (tmp.get(j)>vertToRemove.get(i)){
                        int temp = tmp.get(j);
                        temp--;
                        tmp.set(j, temp);
                    }
                }
            }
        }
    }
}



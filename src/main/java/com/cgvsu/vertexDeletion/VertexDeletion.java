package com.cgvsu.vertexDeletion;

import com.cgvsu.math.Vector3f;
import com.cgvsu.model.*;

import java.util.ArrayList;

public class VertexDeletion {

    public static void deleteVertexes(Model model, ArrayList<Integer> deletedVertex) {
        if (deletedVertex.isEmpty()) return;
        if (model == null || model.vertices.isEmpty()) {
            throw new NullPointerException("Model is empty!");
        }

        //проходим по полигонам модели удаляя все,
        // которые содержат любую вершину из полученного "списка смерти"
        ArrayList<Integer> polygonVertices = new ArrayList<>();

//        int polygonArraySize;

        for (int i = model.polygons.size() - 1; i >= 0; i--) {
            for (int j = 0; j<deletedVertex.size(); j++){
                for (int k : model.polygons.get(i).getVertexIndices()) {
                    if (k == deletedVertex.get(j)) {
                        model.polygons.remove(i);
                        i--;
                        j=deletedVertex.size()+100;
                        break;
                    }
                }
            }
        }


        //Теперь помечаем к удалению "висячие" вершины
        int vertArrSize = model.vertices.size();
        ArrayList<Integer> vertToRemove = new ArrayList<>();

        int del = 1;
        for (int i = 0; i < vertArrSize; i++) {
            del = 1;
            for (int j = 0; j < model.polygons.size(); j++) {
                polygonVertices = model.polygons.get(j).getVertexIndices();
                if (!polygonVertices.isEmpty()) {
                    for (int k : polygonVertices) {
                        if (k == i) {
                            del = 0;
                            break;
                        }
                    }
                    if (del == 0) break;
                }
            }
            if (del == 1) vertToRemove.add(i);
        }
        //Второй проход с правкой значений вертексов в полигонах
        ArrayList<Integer> tmp = new ArrayList<>();
        int tmpVertIndex;
        if (vertToRemove.isEmpty()) return;

        for (int i = vertToRemove.size() - 1; i >= 0; i--) {
            for (int j = 0; j < model.polygons.size(); j++) {
                Polygon p = model.polygons.get(j);
                for (int k = 0; k < p.getVertexIndices().size(); k++) {
                    if (p.getVertexIndices().get(k) > vertToRemove.get(i)) {
                        tmpVertIndex = p.getVertexIndices().get(k);
                        tmpVertIndex--;
                        p.getVertexIndices().set(k, tmpVertIndex);
                    } else {
//                        tmp.add(p.getVertexIndices().get(k));
                    }
                }
            }
            int temp = vertToRemove.get(i);
            model.vertices.remove(temp);
        }
        vertToRemove.clear();
    }
}



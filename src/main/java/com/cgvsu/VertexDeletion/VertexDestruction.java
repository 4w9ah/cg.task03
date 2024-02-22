package com.cgvsu.VertexDeletion;
import com.cgvsu.math.*;
import com.cgvsu.model.*;

import java.util.ArrayList;

public class VertexDestruction {

    public static void deleteVertexes (Model model, ArrayList<Integer> deletedVertex) {
        if (deletedVertex.isEmpty()) return;
        if (model==null || model.vertices.isEmpty()) {
            throw new NullPointerException("Model is empty!");
        };
        //проходим по полигонам модели удаляя все,
        // которые содержат любую вершину из полученного "списка смерти"
        for (Polygon p : model.polygons){
            for (Integer vertex : deletedVertex) {
                if (p.getVertexIndices().contains(vertex)) {
                    model.polygons.remove(p);
                }
            }
        }
        //Теперь удаляем "висячие" вершины
        int arraySize = model.vertices.size();
        for (int i=0; i< arraySize ; i++) {
            boolean remove = true;
            for (Polygon p : model.polygons) {
                if (p.getVertexIndices().contains(i)) {
                    remove = false;
                    break;
                }
            }
            if (remove) {
                model.vertices.remove(i);
                arraySize--;
                i--;
            }
        }
    }
}

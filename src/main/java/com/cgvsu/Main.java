package com.cgvsu;

import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import com.cgvsu.vertexDeletion.VertexDeletion;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Simple3DViewer.main(args);
        ArrayList<Polygon> polyArray = new ArrayList<>();
        ArrayList<Vector3f> v = new ArrayList<>();
        ArrayList<Vector2f> texDummy = new ArrayList<>();
        ArrayList<Vector3f> vectDummy = new ArrayList<>();

        v.add(new Vector3f(-1, -1, 1));
        v.add(new Vector3f(-1, -1, -1));
        v.add(new Vector3f(1, -1, 1));
        v.add(new Vector3f(1, -1, -1));
        v.add(new Vector3f(0, 1, 0));

        Polygon p1 = new Polygon();
        Polygon p2 = new Polygon();
        Polygon p3 = new Polygon();
        Polygon p4 = new Polygon();
        Polygon p5 = new Polygon();

        p1.setVertexIndices(new ArrayList<>(List.of(1,5,2)));
        p2.setVertexIndices(new ArrayList<>(List.of(2,5,4)));
        p3.setVertexIndices(new ArrayList<>(List.of(4,5,3)));
        p4.setVertexIndices(new ArrayList<>(List.of(3,5,1)));
        p5.setVertexIndices(new ArrayList<>(List.of(2,4,4,1)));

        polyArray.add(p1);
        polyArray.add(p2);
        polyArray.add(p3);
        polyArray.add(p4);
        polyArray.add(p5);

//        ArrayList<Integer> p = new ArrayList<>(List.of(1));
        Model pyramid = new Model(v, texDummy, vectDummy, polyArray);


        ArrayList<Integer> deletionTest = new ArrayList<>();

        deletionTest.add(0);
        //deletionTest.add(1);
        System.out.print("hello");
        VertexDeletion.deleteVertexes(pyramid, deletionTest);
        System.out.print(pyramid.polygons.size());
        System.out.print(pyramid.vertices.size());
    }
    //hello
}

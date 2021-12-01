package it.uniba.pioneers.test.grafo;

import android.content.Context;

import com.google.common.graph.Graph;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Grafo implements Serializable {
    MutableValueGraph<StringSerializable, Integer> graph = ValueGraphBuilder.undirected().build();
    ArrayList<String> nodes;
    public Grafo(Context context){
        this.graph.addNode(new StringSerializable("Ciao"));
        this.graph.addNode(new StringSerializable("Prova"));
        this.graph.addNode(new StringSerializable("Uno"));

    }

    public void writeToFile(Context context){
        String pathToAppFolder = context.getExternalFilesDir(null).getAbsolutePath();
        String filePath = pathToAppFolder + File.separator + "graph.ser";
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filePath));
            os.writeObject(graph);
            os.close();
        }
        catch (Exception e) {
            System.out.println("");
        }
    }

    public void readToFile(Context context){
        try {
            String pathToAppFolder = context.getExternalFilesDir(null).getAbsolutePath();
            String filePath = pathToAppFolder + File.separator + "graph.ser";

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));
            this.graph = (MutableValueGraph<String, Integer>) in.readObject();

            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}

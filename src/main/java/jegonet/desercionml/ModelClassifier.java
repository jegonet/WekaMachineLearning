package jegonet.desercionml;

import weka.classifiers.Classifier;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.SerializationHelper;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.DenseInstance;
import java.util.List;
import weka.core.Instances;
import java.util.ArrayList;
import weka.core.Attribute;

public class ModelClassifier
{
    private Attribute edad;
    private Attribute semestre;
    private Attribute asignaturasCursadas;
    private Attribute asignaturasAprobadas;
    private Attribute creditosCursados;
    private Attribute creditosAprobados;
    private ArrayList<Attribute> attributes;
    private ArrayList<String> classVal;
    private Instances dataRaw;
    
    public ModelClassifier() {
        this.edad = new Attribute("Edad");
        this.semestre = new Attribute("Semestre");
        this.asignaturasCursadas = new Attribute("AsignaturasCursadas");
        this.asignaturasAprobadas = new Attribute("AsignaturasAprobadas");
        this.creditosCursados = new Attribute("CreditosCursados");
        this.creditosAprobados = new Attribute("CreditosAprobados");
        this.attributes = new ArrayList<Attribute>();
        (this.classVal = new ArrayList<String>()).add("GRADUADO");
        this.classVal.add("DESERTOR");
        this.classVal.add("ACTIVO");
        this.attributes.add(this.edad);
        this.attributes.add(this.semestre);
        this.attributes.add(this.asignaturasCursadas);
        this.attributes.add(this.asignaturasAprobadas);
        this.attributes.add(this.creditosCursados);
        this.attributes.add(this.creditosAprobados);
        this.attributes.add(new Attribute("Estado", (List)this.classVal));
        (this.dataRaw = new Instances("TestInstances", (ArrayList)this.attributes, 0)).setClassIndex(this.dataRaw.numAttributes() - 1);
    }
    
    public Instances createInstance(final double edad, final double semestre, final double asignaturasCursadas, final double asignaturasAprobadas, final double creditosCursados, final double creditosAprobados) {
        this.dataRaw.clear();
        final double[] instanceValue1 = { edad, semestre, asignaturasCursadas, asignaturasAprobadas, creditosCursados, creditosAprobados, 0.0 };
        this.dataRaw.add((Instance)new DenseInstance(1.0, instanceValue1));
        return this.dataRaw;
    }
    
    public String classifiy(final Instances insts, final int modelIndex) {
        String result = "Not classified!!";
        try {
            Classifier cls;
            if (modelIndex == 0) {
                cls = (Classifier)SerializationHelper.read(ModelClassifier.class.getResource("/randomforest.model").toString().replaceAll("file:", ""));
            }
            else {
                cls = (Classifier)SerializationHelper.read(ModelClassifier.class.getResource("/neuronal.model").toString().replaceAll("file:", ""));
            }
            result = this.classVal.get((int)cls.classifyInstance(insts.firstInstance()));
        }
        catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
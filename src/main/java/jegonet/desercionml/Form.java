package jegonet.desercionml;

import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import java.awt.Component;
import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;

public class Form extends JFrame
{
    JLabel lblModel;
    JComboBox cbModel;
    JLabel lblEdad;
    JSlider slEdad;
    JLabel lblSemestre;
    JSlider slSemestre;
    JLabel lblAsignaturasCur;
    JSlider slAsignaturasCur;
    JLabel lblAsignaturasApro;
    JSlider slAsignaturasApro;
    JLabel lblCreditosCur;
    JSlider slCreditosCur;
    JLabel lblCreditosApro;
    JSlider slCreditosApro;
    JButton btnCerrar;
    JButton btnCalcular;
    ModelClassifier classifierModel;
    
    public Form() {
        super("Tester de Machine Learning con Weka");
        this.initComponentes();
        this.initAcciones();
        this.setLayout(new GridLayout(1, 1));
        this.setVisible(true);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(3);
        this.classifierModel = new ModelClassifier();
    }
    
    public void initComponentes() {
        final JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(8, 2, 5, 5));
        final String[] modelStrings = { "RamdomForest", "Multilayer Perceptron" };
        this.lblModel = new JLabel("Modelo");
        this.cbModel = new JComboBox(modelStrings);
        this.cbModel.setSelectedIndex(0);
        (this.lblEdad = new JLabel()).setToolTipText("Edad");
        DefaultBoundedRangeModel model = new DefaultBoundedRangeModel(15, 0, 15, 80);
        (this.slEdad = new JSlider(model)).setMajorTickSpacing(1);
        this.slEdad.setPaintTicks(true);
        this.setSliderLabel(this.lblEdad, this.slEdad);
        (this.lblSemestre = new JLabel()).setToolTipText("Semestre");
        model = new DefaultBoundedRangeModel(1, 0, 1, 10);
        (this.slSemestre = new JSlider(model)).setMajorTickSpacing(1);
        this.slSemestre.setPaintTicks(true);
        this.setSliderLabel(this.lblSemestre, this.slSemestre);
        (this.lblAsignaturasCur = new JLabel()).setToolTipText("Asignaturas Cursadas");
        model = new DefaultBoundedRangeModel(0, 0, 0, 102);
        (this.slAsignaturasCur = new JSlider(model)).setMajorTickSpacing(1);
        this.slAsignaturasCur.setPaintTicks(true);
        this.setSliderLabel(this.lblAsignaturasCur, this.slAsignaturasCur);
        (this.lblAsignaturasApro = new JLabel()).setToolTipText("Asignaturas Aprobadas");
        model = new DefaultBoundedRangeModel(0, 0, 0, 102);
        (this.slAsignaturasApro = new JSlider(model)).setMajorTickSpacing(1);
        this.slAsignaturasApro.setPaintTicks(true);
        this.setSliderLabel(this.lblAsignaturasApro, this.slAsignaturasApro);
        (this.lblCreditosCur = new JLabel()).setToolTipText("Cr\u00e9ditos Cursados");
        model = new DefaultBoundedRangeModel(0, 0, 0, 290);
        (this.slCreditosCur = new JSlider(model)).setMajorTickSpacing(1);
        this.slCreditosCur.setPaintTicks(true);
        this.setSliderLabel(this.lblCreditosCur, this.slCreditosCur);
        (this.lblCreditosApro = new JLabel()).setToolTipText("Cr\u00e9ditos Aprobados");
        model = new DefaultBoundedRangeModel(0, 0, 0, 290);
        (this.slCreditosApro = new JSlider(model)).setMajorTickSpacing(1);
        this.slCreditosApro.setPaintTicks(true);
        this.setSliderLabel(this.lblCreditosApro, this.slCreditosApro);
        this.btnCerrar = new JButton("Cerrar");
        this.btnCalcular = new JButton("Clasificar");
        newPanel.add(this.lblModel);
        newPanel.add(this.cbModel);
        newPanel.add(this.lblEdad);
        newPanel.add(this.slEdad);
        newPanel.add(this.lblSemestre);
        newPanel.add(this.slSemestre);
        newPanel.add(this.lblAsignaturasCur);
        newPanel.add(this.slAsignaturasCur);
        newPanel.add(this.lblAsignaturasApro);
        newPanel.add(this.slAsignaturasApro);
        newPanel.add(this.lblCreditosCur);
        newPanel.add(this.slCreditosCur);
        newPanel.add(this.lblCreditosApro);
        newPanel.add(this.slCreditosApro);
        newPanel.add(this.btnCerrar);
        newPanel.add(this.btnCalcular);
        newPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        this.add(newPanel);
        newPanel.setVisible(true);
    }
    
    private void initAcciones() {
        this.btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                System.exit(0);                   
            }
        });
        this.btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                final String result = Form.this.classifierModel.classifiy
                    (Form.this.classifierModel.createInstance(Form.this.slEdad.getValue(), 
                            Form.this.slSemestre.getValue(), Form.this.slAsignaturasCur.getValue(), 
                            Form.this.slAsignaturasApro.getValue(), Form.this.slCreditosCur.getValue(), 
                            Form.this.slCreditosApro.getValue()), 
                            Form.this.cbModel.getSelectedIndex()); 
                
                JOptionPane.showMessageDialog(null, "Clasificado como: " + result);
            }
        });
        this.slEdad.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                setSliderLabel(lblEdad, (JSlider)event.getSource());
            }
        });
        this.slSemestre.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                setSliderLabel(lblSemestre, (JSlider)event.getSource());
            }
        });
        this.slAsignaturasCur.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                setSliderLabel(lblAsignaturasCur, (JSlider)event.getSource());
            }
        });
        this.slAsignaturasApro.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                setSliderLabel(lblAsignaturasApro, (JSlider)event.getSource());
            }
        });
        this.slCreditosCur.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                setSliderLabel(lblCreditosCur, (JSlider)event.getSource());
            }
        });
        this.slCreditosApro.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                setSliderLabel(lblCreditosApro, (JSlider)event.getSource());
            }
        });
    }
    
    private void setSliderLabel(final JLabel label, final JSlider slider) {
        label.setText(label.getToolTipText() + " (" +  slider.getValue() + ")" );
    }
}
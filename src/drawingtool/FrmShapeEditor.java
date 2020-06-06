package drawingtool;

import drawingtool.ui.Canvas;
import drawingtool.listener.ShapeListener;
import drawingtool.log.Log;
import drawingtool.io.Document;
import drawingtool.io.parser.DocumentFactory;
import drawingtool.io.parser.FactoryException;
import drawingtool.io.parser.json.JSONDocumentFactory;
import drawingtool.io.transformer.DocumentTransformer;
import drawingtool.io.transformer.json.JSONDocumentTransformer;
import drawingtool.shapes.Arrow;
import drawingtool.shapes.Ellipse;
import drawingtool.shapes.Rectangle;
import drawingtool.shapes.Shape;
import drawingtool.shapes.Text;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author Joao
 */
public class FrmShapeEditor extends javax.swing.JFrame {

    Canvas canvas = new Canvas();

    public FrmShapeEditor() {
        initComponents();

        pnlDrawer.add(canvas);
        this.setBounds(0, 0, 720, 400);

        canvas.addShapeListener(new ShapeListener() {
            @Override
            public void onSelect(Shape shape) {

            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDrawer = new javax.swing.JPanel();
        btnAddRectangle = new javax.swing.JButton();
        spnRotationAngle = new javax.swing.JSpinner();
        btnZoomIn = new javax.swing.JButton();
        btnZoomOut = new javax.swing.JButton();
        lblZoom = new javax.swing.JLabel();
        btnAddEllipse = new javax.swing.JButton();
        btnAddArrow = new javax.swing.JButton();
        lblRotationAngle = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        btnAddText = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Canvas Demo");

        pnlDrawer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlDrawer.setLayout(new java.awt.BorderLayout());

        btnAddRectangle.setText("Add Rectangle");
        btnAddRectangle.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnAddRectangle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRectangleActionPerformed(evt);
            }
        });

        spnRotationAngle.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(360.0f), Float.valueOf(1.0f)));

        btnZoomIn.setText("+");
        btnZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomInActionPerformed(evt);
            }
        });

        btnZoomOut.setText("-");
        btnZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomOutActionPerformed(evt);
            }
        });

        lblZoom.setText("Zoom: 1.0");

        btnAddEllipse.setText("Add Ellipse");
        btnAddEllipse.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnAddEllipse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEllipseActionPerformed(evt);
            }
        });

        btnAddArrow.setText("Add Arrow");
        btnAddArrow.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnAddArrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddArrowActionPerformed(evt);
            }
        });

        lblRotationAngle.setText("Rotation angle");

        btnSave.setText("Save");
        btnSave.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnLoad.setText("Load");
        btnLoad.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        btnAddText.setText("Add Text");
        btnAddText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDrawer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLoad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddRectangle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddEllipse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddArrow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRotationAngle)
                .addGap(9, 9, 9)
                .addComponent(spnRotationAngle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnZoomIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnZoomOut)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddRectangle)
                        .addComponent(spnRotationAngle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnZoomIn)
                        .addComponent(btnZoomOut)
                        .addComponent(btnAddEllipse)
                        .addComponent(btnAddArrow)
                        .addComponent(lblRotationAngle)
                        .addComponent(btnSave)
                        .addComponent(btnLoad)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDrawer, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(lblZoom))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddRectangleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRectangleActionPerformed
        Rectangle rectangle = new Rectangle(20, 150, 200, 150);
        rectangle.setAngle(Float.valueOf(String.valueOf(spnRotationAngle.getValue())));
        canvas.addShape(rectangle);
    }//GEN-LAST:event_btnAddRectangleActionPerformed

    private void btnZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomInActionPerformed
        canvas.setZoom(canvas.getZoom() + 0.1f);
        lblZoom.setText("Zoom: " + canvas.getZoom());
    }//GEN-LAST:event_btnZoomInActionPerformed

    private void btnZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomOutActionPerformed
        canvas.setZoom(canvas.getZoom() - 0.1f);
        lblZoom.setText("Zoom: " + canvas.getZoom());
    }//GEN-LAST:event_btnZoomOutActionPerformed

    private void btnAddEllipseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEllipseActionPerformed
        Ellipse ellipse = new Ellipse(20, 150, 200, 150);
        ellipse.setAngle(Float.valueOf(String.valueOf(spnRotationAngle.getValue())));
        canvas.addShape(ellipse);
    }//GEN-LAST:event_btnAddEllipseActionPerformed

    private void btnAddArrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddArrowActionPerformed
        Arrow arrow = new Arrow(20, 150, 200, 150);
        arrow.setAngle(Float.valueOf(String.valueOf(spnRotationAngle.getValue())));
        canvas.addShape(arrow);
    }//GEN-LAST:event_btnAddArrowActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        FileWriter fileWriter = null;
        try {
            DocumentTransformer transformer = new JSONDocumentTransformer();
            fileWriter = new FileWriter("example.json");
            transformer.transform(canvas.getDocument(), fileWriter);
        } catch (IOException ex) {
            Log.LOGGER.log(Level.SEVERE, "Error writing file", ex);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                Log.LOGGER.log(Level.SEVERE, "Error closing file", ex);
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        InputStream is = null;
        try {
            //Creates the document factory
            DocumentFactory factory = new JSONDocumentFactory();
            //Read the file
            is = new FileInputStream(new File("example.json"));
            //Creates the document
            Document document = factory.createDocument(is);
            //Sets the document in Canvas
            canvas.setDocument(document);
        } catch (FileNotFoundException | FactoryException ex) {
            Log.LOGGER.log(Level.SEVERE, "Error loading file", ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Log.LOGGER.log(Level.SEVERE, "Error closing file", ex);
            }
        }
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnAddTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTextActionPerformed
        Text text = new Text(20, 150, 200, 150);
        text.setAngle(Float.valueOf(String.valueOf(spnRotationAngle.getValue())));

        Object value = JOptionPane.showInputDialog(this, "Enter the text");
        if (value != null) {
            text.setText(String.valueOf(value));
        }
        canvas.addShape(text);
    }//GEN-LAST:event_btnAddTextActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddArrow;
    private javax.swing.JButton btnAddEllipse;
    private javax.swing.JButton btnAddRectangle;
    private javax.swing.JButton btnAddText;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnZoomIn;
    private javax.swing.JButton btnZoomOut;
    private javax.swing.JLabel lblRotationAngle;
    private javax.swing.JLabel lblZoom;
    private javax.swing.JPanel pnlDrawer;
    private javax.swing.JSpinner spnRotationAngle;
    // End of variables declaration//GEN-END:variables
}

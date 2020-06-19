package drawingtool.demo;

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
import drawingtool.shapes.Image;
import drawingtool.shapes.Rectangle;
import drawingtool.shapes.Shape;
import drawingtool.shapes.Text;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author Joao
 */
public class FrmShapeEditor extends javax.swing.JFrame {

    private Canvas canvas = new Canvas();
    private PopupMenuShapeOption popupMenuShapeOption = new PopupMenuShapeOption();

    public FrmShapeEditor() {
        initComponents();

        pnlDrawer.add(canvas);
        this.setBounds(0, 0, 820, 400);

        canvas.addShapeListener(new ShapeListener() {
            @Override
            public void onSelect(Shape shape) {

            }
        });
        
        //Load the shape options
        loadShapeOptions();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDrawer = new javax.swing.JPanel();
        spnRotationAngle = new javax.swing.JSpinner();
        btnZoomIn = new javax.swing.JButton();
        btnZoomOut = new javax.swing.JButton();
        lblZoom = new javax.swing.JLabel();
        lblRotationAngle = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        btnAddShape = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Canvas Editor Demo");

        pnlDrawer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlDrawer.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlDrawerComponentResized(evt);
            }
        });
        pnlDrawer.setLayout(null);

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

        btnAddShape.setText("Add Shape");
        btnAddShape.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnAddShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddShapeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDrawer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLoad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddShape)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRotationAngle)
                .addGap(9, 9, 9)
                .addComponent(spnRotationAngle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnZoomIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnZoomOut)
                .addGap(0, 80, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnRotationAngle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnZoomIn)
                    .addComponent(btnZoomOut)
                    .addComponent(lblRotationAngle)
                    .addComponent(btnSave)
                    .addComponent(btnLoad)
                    .addComponent(btnAddShape))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDrawer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(lblZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomInActionPerformed
        canvas.setZoom(canvas.getZoom() + 0.1f);
        lblZoom.setText("Zoom: " + canvas.getZoom());
    }//GEN-LAST:event_btnZoomInActionPerformed

    private void btnZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomOutActionPerformed
        canvas.setZoom(canvas.getZoom() - 0.1f);
        lblZoom.setText("Zoom: " + canvas.getZoom());
    }//GEN-LAST:event_btnZoomOutActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        FileWriter fileWriter = null;
        try {
            DocumentTransformer transformer = new JSONDocumentTransformer();
            fileWriter = new FileWriter("example.json");
            transformer.transform(canvas.getDocument(), fileWriter);
        } catch (Exception ex) {
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

    private void btnAddShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddShapeActionPerformed
        popupMenuShapeOption.show(btnAddShape, 0, btnAddShape.getHeight());
    }//GEN-LAST:event_btnAddShapeActionPerformed

    private void pnlDrawerComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlDrawerComponentResized
        canvas.setBounds(0, 0, this.pnlDrawer.getWidth(),
                this.pnlDrawer.getHeight());
    }//GEN-LAST:event_pnlDrawerComponentResized

    private void loadShapeOptions() {
        popupMenuShapeOption.addShapeOption("Rectangle", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Rectangle rectangle = new Rectangle(20, 150, 200, 150);
                    rectangle.setAngle(Float.valueOf(
                            String.valueOf(spnRotationAngle.getValue())));
                    canvas.addShape(rectangle);     
                } catch (Exception ex) {
                    Log.LOGGER.log(Level.SEVERE, "Error adding rectangle", ex);
                }
            }
        });

        popupMenuShapeOption.addShapeOption("Ellipse", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Ellipse ellipse = new Ellipse(20, 150, 200, 150);
                    ellipse.setAngle(Float.valueOf(String.valueOf(spnRotationAngle.getValue())));
                    canvas.addShape(ellipse);
                } catch (Exception ex) {
                    Log.LOGGER.log(Level.SEVERE, "Error adding ellipse", ex);
                }
            }
        });

        popupMenuShapeOption.addShapeOption("Arrow", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Arrow arrow = new Arrow(20, 150, 200, 150);
                    arrow.setAngle(Float.valueOf(String.valueOf(spnRotationAngle.getValue())));
                    canvas.addShape(arrow);
                } catch (Exception ex) {
                    Log.LOGGER.log(Level.SEVERE, "Error adding arrow", ex);
                }
            }
        });

        popupMenuShapeOption.addShapeOption("Text", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Text text = new Text(20, 150, 200, 150);

                    text.setAngle(Float.valueOf(String.valueOf(spnRotationAngle.getValue())));
                    //Requests the text
                    Object value = JOptionPane.showInputDialog(FrmShapeEditor.this,
                            "Enter the text", "Insert Text", JOptionPane.QUESTION_MESSAGE);
                    //Validate the text
                    if (value == null || String.valueOf(value).isBlank()) {
                        return;
                    }
                    text.setText(String.valueOf(value));
                    canvas.addShape(text);
                } catch (Exception ex) {
                    Log.LOGGER.log(Level.SEVERE, "Error adding text", ex);
                }
            }
        });

        popupMenuShapeOption.addShapeOption("Image", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Image image = new Image(new File("test/loremipsum.png"), 20, 150, 200, 150);
                    image.setAngle(Float.valueOf(String.valueOf(spnRotationAngle.getValue())));
                    canvas.addShape(image);
                } catch (Exception ex) {
                    Log.LOGGER.log(Level.SEVERE, "Error adding image", ex);
                }
            }
        });
    }

    class PopupMenuShapeOption extends JPopupMenu {

        private JPanel pnlShapeOptions;
        private int colNumbers = 3;
        private int horizontalGap = 5;
        private int verticalGap = 5;
        private int popupWidth = 260;
        private int btnOptionWidth = 80;
        private int btnOptionHeight = 25;

        public PopupMenuShapeOption() {
            this.pnlShapeOptions = new JPanel();
            this.pnlShapeOptions.setLayout(new FlowLayout(
                    FlowLayout.LEFT, this.horizontalGap, this.verticalGap));
            this.insert(this.pnlShapeOptions, 0);
        }

        public void addShapeOption(String name, ActionListener action) {
            JButton btnShapeOption = new JButton(name);
            btnShapeOption.setPreferredSize(
                    new Dimension(btnOptionWidth, btnOptionHeight));
            btnShapeOption.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnShapeOption.addActionListener(action);
            btnShapeOption.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    popupMenuShapeOption.setVisible(false);
                    canvas.repaint();
                }
            });
            btnShapeOption.setMargin(new Insets(2, 2, 2, 2));
            this.pnlShapeOptions.add(btnShapeOption);

            //Calculates the new row number
            int rowNumbers = (int) ((float) this.pnlShapeOptions.getComponentCount()
                    / (float) colNumbers + 0.9);
            //Calculates the new Popup size
            this.pnlShapeOptions.setPreferredSize(new Dimension(popupWidth,
                    (rowNumbers * (btnOptionHeight + this.verticalGap)) + this.verticalGap));
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddShape;
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

package drawingtool;

import drawingtool.interactor.CursorInteractor;
import drawingtool.interactor.MoveInteractor;
import drawingtool.interactor.PickerInteractor;
import drawingtool.interactor.ResizeInteractor;
import drawingtool.listener.ShapeListener;
import drawingtool.shapes.Arrow;
import drawingtool.shapes.Ellipse;
import drawingtool.shapes.Rectangle;
import drawingtool.shapes.Shape;
import drawingtool.shapes.Text;

/**
 *
 * @author Joao
 */
public class FrmShapeEditor extends javax.swing.JFrame {

    Canvas canvas = new Canvas();

    public FrmShapeEditor() {
        initComponents();

        pnlDrawer.add(canvas);
        this.setBounds(0, 0, 600, 400);

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDrawer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnAddRectangle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddEllipse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddArrow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRotationAngle)
                .addGap(9, 9, 9)
                .addComponent(spnRotationAngle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnZoomIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnZoomOut)
                .addGap(0, 130, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddRectangle)
                    .addComponent(spnRotationAngle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnZoomIn)
                    .addComponent(btnZoomOut)
                    .addComponent(btnAddEllipse)
                    .addComponent(btnAddArrow)
                    .addComponent(lblRotationAngle))
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddArrow;
    private javax.swing.JButton btnAddEllipse;
    private javax.swing.JButton btnAddRectangle;
    private javax.swing.JButton btnZoomIn;
    private javax.swing.JButton btnZoomOut;
    private javax.swing.JLabel lblRotationAngle;
    private javax.swing.JLabel lblZoom;
    private javax.swing.JPanel pnlDrawer;
    private javax.swing.JSpinner spnRotationAngle;
    // End of variables declaration//GEN-END:variables
}

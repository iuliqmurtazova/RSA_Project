package thread.calculation;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class JFrame_Panel extends javax.swing.JFrame {
  
    public JFrame_Panel() {
        initComponents();
        init();
    }
    
    private void init(){
        setTitle("E Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setVisible(true);
    }
    NeperNumberCalculator number = new NeperNumberCalculator();
    NumberWriter write = new NumberWriter();
   
    @SuppressWarnings("unchecked")                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        calculate_button = new javax.swing.JButton();
        memberField = new javax.swing.JTextField();
        threadField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); 
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("Please enter the number of members:");

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); 
        jLabel2.setForeground(new java.awt.Color(0, 153, 255));
        jLabel2.setText("Please enter the number of threads:");

        calculate_button.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); 
        calculate_button.setForeground(new java.awt.Color(0, 153, 255));
        calculate_button.setText("Calculate");
        calculate_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculate_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(memberField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(threadField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(calculate_button)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(memberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(threadField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(calculate_button)
                .addGap(27, 27, 27))
        );

        pack();
    }                      

    private void calculate_buttonActionPerformed(java.awt.event.ActionEvent evt) {
    	final String SYMBOLS = "[a-zA-Z#$%=@!{},`~&*()'<>?.:;_|^/+\\t\\r\\n\\[\\]\"-]+";
    	if(memberField.getText().equals("") || threadField.getText().equals("")
    	   || memberField.getText().matches(SYMBOLS) 
    	   || threadField.getText().matches(SYMBOLS)){
    	   JOptionPane.showMessageDialog(null,"Please enter correct values!");
    	   System.exit(0);
    	}
    	
    	final int MEMBERS = Integer.parseInt(memberField.getText());
    	final int THREADS = Integer.parseInt(threadField.getText());
    	
    	double time = System.currentTimeMillis();
    	String linearResult = number.calculateNeperNumberLinear(MEMBERS).toString();
    	double timeRes = (System.currentTimeMillis()-time);
    	
    	double time1 = System.currentTimeMillis();
    	String asynchronousResult = number.calculateNeperNumberAsynch(MEMBERS, THREADS).toString();
    	double time1Res = (System.currentTimeMillis()-time1);
    	
        JOptionPane.showMessageDialog(null,
        "With linear calculation e= "+ linearResult + 
        "\nTime for linear calculation: "+ timeRes + "ms");
       
        JOptionPane.showMessageDialog(null,
        "With asynch calculation e = " + asynchronousResult + 
        "\nTime for calculation with " + THREADS + " threads is " + time1Res + "ms");
       
    	write.writing("Linear result: " + linearResult + "\r\n" +
    			"Asynchronous result: " + asynchronousResult);
        
        double Sp = timeRes/time1Res;
        double Ep = time1Res/THREADS;
        System.out.println("Sp = " + Sp + "\nEp = " + Ep);
        System.exit(0);
    }                                                
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrame_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame_Panel().setVisible(true);
            }
        });
    }
    
    private javax.swing.JButton calculate_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField memberField;
    private javax.swing.JTextField threadField;                  
}
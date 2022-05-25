import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReturnBook extends  JFrame implements ActionListener {
    JPanel contentPane;
    JTextField t1, t2, t3, t4, t5;
    JButton b1, b2,b3,b4;

    public ReturnBook() {
        setTitle("Return Book");
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel l1 = new JLabel("Student Id:");
        l1.setBounds(50, 100, 100, 30);
        contentPane.add(l1);

        b3=new JButton("Search");
        b3.setBounds(430,100,80,30);
        b3.setBackground(Color.black);
        b3.setForeground(Color.white);
        b3.addActionListener(this);
        contentPane.add(b3);

        JLabel l2 = new JLabel("Student Name:");
        l2.setBounds(50, 150, 100, 30);
        contentPane.add(l2);

        JLabel l3 = new JLabel("Book Id");
        l3.setBounds(50, 200, 100, 30);
        contentPane.add(l3);

        b4=new JButton("Search");
        b4.setBounds(430,200,80,30);
        b4.setBackground(Color.black);
        b4.setForeground(Color.white);
        b4.addActionListener(this);
        contentPane.add(b4);

        JLabel l4 = new JLabel("Book Name:");
        l4.setBounds(50, 250, 100, 30);
        contentPane.add(l4);

        JLabel l5=new JLabel("Issue Date:");
        l5.setBounds(50,300,100,30);
        contentPane.add(l5);


        t1 = new JTextField();
        t1.setBounds(200, 100, 200, 30);
        contentPane.add(t1);


        t2 = new JTextField();
        t2.setBounds(200, 150, 200, 30);
        t2.setEditable(false);
        contentPane.add(t2);

        t3 = new JTextField();
        t3.setBounds(200, 200, 200, 30);
        contentPane.add(t3);

        t4 = new JTextField();
        t4.setBounds(200, 250, 200, 30);
        t4.setEditable(false);
        contentPane.add(t4);

        t5 = new JTextField();
        t5.setBounds(200, 300, 200, 30);
        t5.setEditable(false);
        contentPane.add(t5);



        b1 = new JButton("Return");
        b1.addActionListener(this);
        b1.setBounds(100, 400, 100, 30);
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.black);
        contentPane.add(b1);

        b2 = new JButton("Back");
        b2.addActionListener(this);
        b2.setBounds(300, 400, 100, 30);
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.black);
        contentPane.add(b2);


        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(Color.yellow, 2, true), "Return-Book",
                TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
        panel.setBackground(Color.lightGray);
        panel.setBounds(10, 30, 560, 500);
        contentPane.add(panel);


        setLayout(null);
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new ReturnBook();
    }
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn con = new Conn();

            if (ae.getSource()==b3) {
                String sql = "SELECT * FROM `issue book` WHERE `Student Id`=?";
                PreparedStatement st = con.c.prepareStatement(sql);
                st.setString(1, t1.getText());
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    t2.setText(rs.getString("Student Name"));

                }
                st.close();
                rs.close();

            }
            if (ae.getSource()==b4){
                String sql = "SELECT * FROM `issue book` WHERE `Book Id`=?";
                PreparedStatement st = con.c.prepareStatement(sql);
                st.setString(1, t3.getText());
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    t4.setText(rs.getString("Book Name"));
                    t5.setText(rs.getString("Date"));

                }
                st.close();
                rs.close();


            }

            if (ae.getSource() == b1) {
                String sql = "INSERT INTO `return book`(`Student Id`, `Student Name`, `Book Id`, `Book Name`, `Date`) VALUES (?,?,?,?,?)";
                PreparedStatement st = con.c.prepareStatement(sql);

                st.setString(1, t1.getText());
                st.setString(2, t2.getText());
                st.setString(3, t3.getText());
                st.setString(4, t4.getText());
                st.setString(5, t5.getText());



                int i = st.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(b1, "Book Returned Successfully!!");
                }


            }
            if (ae.getSource() == b2) {
                this.setVisible(false);
                new Details().setVisible(true);

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

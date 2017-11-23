package local.skynet.views;
import local.skynet.controls.WindowsTenFix;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools extends JPanel {
    private JLabel password;
    private JLabel tipPassword;
    private JButton computePassword;
    private JButton win10Btn;

    private JLabel win10FIX;


    public Tools(){
        password = new JLabel("");
        tipPassword = new JLabel("Xstore Daily Password");
        computePassword = new JButton("Compute");

        win10FIX = new JLabel("Windows 10 configuration");
        win10Btn = new JButton("DTVINST FILES");
        
        GroupLayout groupLayout = new GroupLayout(this);

        setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tipPassword)
                        .addComponent(win10FIX)
                )
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(password)
                        .addComponent(computePassword,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addComponent(win10Btn,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                )
        );
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(tipPassword)
                        .addComponent(password)
                )
                .addComponent(computePassword)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(win10FIX)
                        .addComponent(win10Btn)
                )
        );




        computePassword.addActionListener((e)->{
            Date date = new Date();

            SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
            String year = yearFormat.format(date);
            int yy = Integer.parseInt(year);

            SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
            String month = monthFormat.format(date);
            int mm = Integer.parseInt(month);

            SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
            String day = dayFormat.format(date);
            int dd = Integer.parseInt(day);

            String res = String.format("%d%d%d",mm*2,dd*2,(yy*2+8));
            password.setForeground(Color.RED);
            password.setText(res);




        });
        win10Btn.addActionListener(e->{new WindowsTenFix().perform();});


    }
}

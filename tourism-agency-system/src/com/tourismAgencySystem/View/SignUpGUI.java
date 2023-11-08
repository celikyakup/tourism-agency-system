package com.tourismAgencySystem.View;

import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_singup_name;
    private JTextField fld_signup_uname;
    private JTextField fld_signup_pass;
    private JTextField fld_signup_passagain;
    private JButton btn_signup;
    private JButton btn_logout;

    public SignUpGUI(){
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITTLE);
        setVisible(true);
        btn_signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFldEmpty(fld_singup_name)||Helper.isFldEmpty(fld_signup_uname)||Helper.isFldEmpty(fld_signup_pass)){
                    Helper.showMsg("fill");
                }
                else {
                    if (!fld_signup_pass.getText().equals(fld_signup_passagain.getText())){
                        Helper.showMsg("Şifreler uyuşmuyor");
                    }
                    else {
                        if (User.add(fld_singup_name.getText(),fld_signup_uname.getText(),fld_signup_pass.getText())){
                            Helper.showMsg("done");
                            dispose();
                            LoginGUI log=new LoginGUI();
                        }
                    }
                }
            }
        });
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginGUI log=new LoginGUI();
            }
        });
    }

    public static void main(String[] args) {
        SignUpGUI sign=new SignUpGUI();
    }
}


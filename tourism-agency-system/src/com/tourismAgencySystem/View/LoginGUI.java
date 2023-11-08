package com.tourismAgencySystem.View;

import com.tourismAgencySystem.Helper.Config;
import com.tourismAgencySystem.Helper.Helper;
import com.tourismAgencySystem.Model.Admin;
import com.tourismAgencySystem.Model.AgencyPerson;
import com.tourismAgencySystem.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private JTextField fld_user_uname;
    private JPasswordField fld_user_pass;
    private JButton btn_login;
    private JButton btn_signup;


    public LoginGUI(){
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITTLE);
        setResizable(false);
        setVisible(true);

        btn_login.addActionListener(new ActionListener() {
            //Değerlendirme Kriteri 8:Login işleminde kullanıcının kaydı olup olmadığı kontrol ediliyor, kaydı yoksa ya da
            //hatalı giriş yapıldıysa kullanıcıya hatalı giriş mesajı veriliyor
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFldEmpty(fld_user_uname) || Helper.isFldEmpty(fld_user_pass)){
                    Helper.showMsg("fill");
                }
                else {
                    User u=User.getFetch(fld_user_uname.getText(),fld_user_pass.getText());
                    if (u==null){
                        Helper.showMsg("Böyle bir kullaanıcı bulunamadı.");
                    }
                    else {
                        switch (u.getType()){
                            case "admin"->{
                                AdminGUI ad=new AdminGUI((Admin) u);
                            }
                            case "AgencyPerson"->{
                                MainGUI m= new MainGUI(u);
                            }
                        }
                        dispose();
                    }
                }
            }
        });
        btn_signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpGUI sign=new SignUpGUI();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI login=new LoginGUI();
    }
}


package com.tourismAgencySystem.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
            if ("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }
    public static int screenCenterPoint(String axis, Dimension size){
        int point;
        switch (axis){
            case "x"->{
                point=(Toolkit.getDefaultToolkit().getScreenSize().width-size.width)/2;
            }
            case "y"->{
                point=(Toolkit.getDefaultToolkit().getScreenSize().height-size.height)/2;
            }
            default -> point=0;
        }
        return point;
    }
    public static boolean isFldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }
    public static void showMsg(String str){
        optionPageTR();
        String msg;
        String title;
        switch (str){
            case "fill" ->{
                msg="Lütfen tüm alanları doldurunuz!";
                title="Hata!";
            }
            case "done" ->{
                msg="İşlem başarılı .";
                title="Sonuç";
            }
            case "error"->{
                msg="Bir hata oluştu.";
                title="Hata";
            }
            default -> {
                msg=str;
                title="Mesaj";
            }
        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean confirm(String str){
        optionPageTR();
        String msg;
        switch (str){
            case "sure"->{
                msg="Bu işlemi gerçekleştirmek istediğinize emin misiniz?";
            }
            default -> msg=str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Son karar",JOptionPane.YES_NO_OPTION) ==0;
    }
    public static void optionPageTR(){
        UIManager.put("OptionPane.okButtonText","Tamam");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");
    }
}

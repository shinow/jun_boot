package com.neusoft.web.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {

    private final String salt = "-l-p-s-";
    /**
     * 对输入的密码加密过程
     */
    @Override
    public String encode(CharSequence charSequence) {
        String password = DigestUtils.md5Hex(DigestUtils.md5Hex(salt + charSequence) + salt);
        return password;
    }

    /**
     * 密码校验过程
     */
    @Override
    public boolean matches(CharSequence inputPwd, String dbPwd) {
        String pass = this.encode(inputPwd);
        if(dbPwd.contentEquals(pass)){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {

        encryptPassword();
    }
    public static void encryptPassword(){
        System.out.println(DigestUtils.md5Hex(DigestUtils.md5Hex("-l-p-s-" + "admin123") + "-l-p-s-"));

    }
}

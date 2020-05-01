package com.czbank.service;

import com.alibaba.fastjson.JSON;
import com.czbank.ProviderApplicatiion;
import com.czbank.dao.StudentDao;
import com.czbank.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;


/**
 * @author foreverActiveBoy
 * @date 2020/4/26 19:31
 * @apiNote
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = {ProviderApplicatiion.class})
@Slf4j
public class StudentServiceTest {
    @Resource
    private StudentDao studentDao;

    /**
     * 根据条件查询
     */
    @Test
    public void queryList() {
        Student student = Student.builder()
                .name("曹操")
                .build();
        List<Student> studentList = studentDao.queryAll(student);
        studentList.forEach((s)->{
            log.info("学生对象为:[{}]",s);
        });
    }

    /**
     * 查询学生对象总分数
     */
    @Test
    public void selectScoreSum(){
        Student student = studentDao.selectScoreSumByName("曹操");
        log.info("学生对象:[{}]",JSON.toJSONString(student));
    }

    /**
     * 一对多关联查询
     */
    @Test
    public void queryStudentAdress(){
        List<Student> studentList = studentDao.selectAdressList("曹操");
        studentList.forEach((s)->{
            log.info("学生对象:[{}]", JSON.toJSONString(s));
        });
    }

    @Test
    public void sign() throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
        //  生成密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024,new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();
        //  私钥
        String privateKey = Base64.encodeBase64String(aPrivate.getEncoded());
        //  公钥
        String publicKey = Base64.encodeBase64String(aPublic.getEncoded());

        //  签名
        PublicKey publicKey1 = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(publicKey)));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey1);
        String signData = Base64.encodeBase64String(cipher.doFinal("测试加密".getBytes()));

        //  解密
        PrivateKey privateKey1 = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey)));
        cipher.init(Cipher.DECRYPT_MODE,privateKey1);
        String srcData = new String(cipher.doFinal(Base64.decodeBase64(signData)));
        System.out.println(srcData+"*********"+signData);
    }
}

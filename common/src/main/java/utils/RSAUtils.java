package utils;

import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA签名工具类
 *
 * @author MaFuxin
 * @date 2018/3/6 10:37
 */
public class RSAUtils {

    /**
     * 签名处理
     * 
     * @param prikeyvalue：私钥
     * @param sign_str：签名源内容
     * @return
     */
    /*public static String sign(String prikeyvalue, String sign_str) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.getBytesBASE64(prikeyvalue));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey myprikey = keyf.generatePrivate(priPKCS8);
            // 用私钥对信息生成数字签名
            java.security.Signature signet = java.security.Signature.getInstance("MD5withRSA");
            signet.initSign(myprikey);
            signet.update(sign_str.getBytes("UTF-8"));
            byte[] signed = signet.sign(); // 对信息的数字签名
            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(signed));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /**
     * 签名验证
     *
     * @param pubkeyvalue：公钥
     * @param oid_str：源内容
     * @param signed_str：签名结果串
     * @return
     */
    /*public static boolean checksign(String pubkeyvalue, String oid_str, String signed_str) {
        try {
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(
                    Base64.getBytesBASE64(pubkeyvalue));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
            byte[] signed = Base64.getBytesBASE64(signed_str);// 这是SignatureData输出的数字签�?
            java.security.Signature signetcheck = java.security.Signature.getInstance("MD5withRSA");
            signetcheck.initVerify(pubKey);
            signetcheck.update(oid_str.getBytes("UTF-8"));
            return signetcheck.verify(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }*/

    /**
     * 转换公钥
     *
     * 使用getPublicKey得到公钥,返回类型为PublicKey
     * @param pubkeyvalue String to PublicKey
     * @return
     */
    public static PublicKey getPublicKey(String pubkeyvalue) {
        try {
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(
                    new BASE64Decoder().decodeBuffer(pubkeyvalue));
            KeyFactory keyFactory = null;
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
            return pubKey;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转换私钥
     *
     * 使用getPrivateKey得到私钥,返回类型为PrivateKey
     * @param key String to PublicKey
     * @return
     */
    /*public PrivateKey getPrivateKey(String key) {
        X509EncodedKeySpec bobPriKeySpec = new X509EncodedKeySpec(
                Base64.getBytesBASE64(key));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey  = keyFactory.generatePrivate(bobPriKeySpec);
            return privateKey;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
*/


}

package utils;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA加解密工具类
 * @author wangb
 * @version 1.0.0
 * @since 2019/7/4
 */
public class RSAUtil {

    /**
     * 加密算法RSA名称
     */
    private static final String RSA = "RSA";

    /**
     * 签名算法
     */
    private static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 公钥key名称
     */
    private static final String PUBLIC_KEY = "public_key";

    /**
     * 私钥key名称
     */
    private static final String PRIVATE_KEY = "private_key";

    private static final Base64.Encoder encoderBase64  = Base64.getEncoder();

    private static final Base64.Decoder decoderBase64  = Base64.getDecoder();

    public static void main(String[] args) throws Exception{
        Map<String,String> map = getRsaKeyPairMap();
        String testStr = "测试RSA加解密";
        String testByte = encryptByPrivateKey(testStr.getBytes(),map.get(PRIVATE_KEY));
        System.out.println(testByte);
        testStr = decryptByPublicKey(testByte.getBytes(),map.get(PUBLIC_KEY));
        System.out.println(testStr);

        testByte = encryptByPublicKey(testStr.getBytes(),map.get(PUBLIC_KEY));
        System.out.println(testByte);
        testStr = decryptByPrivateKey(testByte.getBytes(),map.get(PRIVATE_KEY));
        System.out.println(testStr);

    }

    public static Map<String,String>  getRsaKeyPairMap() throws NoSuchAlgorithmException{
        return genKeyPair();
    }


    /**
     * 使用公钥解密
     * @param content   待解密内容
     * @param publicKey 公钥
     * @return 解密内容
     */
    public static String decryptByPublicKey(byte[] content, String publicKey) {
        try {
            PublicKey pubKey = getPublicKey(publicKey);
            return bolckDecrypt(pubKey,content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用私钥解密
     * @param content   待解密内容
     * @param privateKey 私钥
     * @return 解密内容
     */
    public static String decryptByPrivateKey(byte[] content, String privateKey) {
        try {
            PrivateKey priKey = getPrivateKey(privateKey);
            return bolckDecrypt(priKey,content);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    /**
     * 使用公钥加密
     * 注意：相同参数，每次生成加密结果不一样
     * @param content   待加密内容
     * @param publicKey 公钥
     * @return 经过 base64编码处理后的加密字符串
     */
    public static String encryptByPublicKey(byte[] content, String publicKey) {
        try {
            PublicKey pubKey = getPublicKey(publicKey);
            return bolckEncrypt(content, pubKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用私钥加密
     * @param content   待加密内容
     * @param privateKey 公钥
     * @return 经过 base64编码处理后的加密字符串
     */
    public static String encryptByPrivateKey(byte[] content, String privateKey) {
        try {
            PrivateKey priKey = getPrivateKey(privateKey);
            return bolckEncrypt(content, priKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 分段加密n
     * 公钥、私钥分段加密公用
     * @param cleartextBytes  明文字节数组
     * @param key 加密秘钥
     * @return 秘文
     */
    private static String bolckEncrypt(byte[] cleartextBytes, Key key){
        try {
            // Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance(RSA);
            // 根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] resultBytes = bolckEnDecrypt(cipher,cleartextBytes,MAX_ENCRYPT_BLOCK);
            return encoderBase64.encodeToString(resultBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 分段解密
     * 公钥、私钥分段解密公用
     * @param key 解密密钥对象
     * @param ciphertextBytes 密文字节数组
     * @return 明文
     */
    private static String bolckDecrypt(Key key, byte[] ciphertextBytes){
        try {
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, key);
            ciphertextBytes = decoderBase64.decode(ciphertextBytes);
            byte[] resultBytes = bolckEnDecrypt(cipher,ciphertextBytes,MAX_DECRYPT_BLOCK);
            return new String(resultBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 分段加密、解密具体实现
     * @param cipher 加密、解密工
     * @param edncryptbytes  要分段加密、解密的完整明文字节数组
     * @param segment 加密、解密段
     * @return 加密后的字节数组
     */
    private static byte[] bolckEnDecrypt(Cipher cipher, byte[] edncryptbytes,int segment){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            int inputLen = edncryptbytes.length;
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > segment) {
                    cache = cipher.doFinal(edncryptbytes, offSet, segment);
                } else {
                    cache = cipher.doFinal(edncryptbytes, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * segment;
            }
            return out.toByteArray();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取私钥对象
     * @param privateKey 私钥
     */
    private static PrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException{
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PKCS8EncodedKeySpec privatekcs8KeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
        return keyFactory.generatePrivate(privatekcs8KeySpec);
    }


    /**
     * 获取公钥对象
     * @param publicKey 公钥
     */
    private static PublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException,InvalidKeySpecException{
        byte[] decoded = decoderBase64.decode(publicKey);
        return KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(decoded));
    }

    /**
     * 随机生成密钥对
     */
    private static Map<String,String> genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中

        KeyPair keyPair = keyPairGen.generateKeyPair();
        //获取公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //获取公钥字符串
        String publicKeyString = new String(encoderBase64.encode(publicKey.getEncoded()));
        //获取私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //获取私钥字符串
        String privateKeyString = new String(encoderBase64.encode((privateKey.getEncoded())));
        Map<String,String> keyPairMap = new HashMap<>(2);
        keyPairMap.put(PUBLIC_KEY,publicKeyString);
        keyPairMap.put(PRIVATE_KEY,privateKeyString);
        return keyPairMap;
    }

    /**
     * sign验签
     *
     * @param content 签名原文本
     * @param publicKey 签名公钥
     * @return 密文
     */
    public static boolean verifySign(String content, String publicKey,String sign) throws Exception{
        byte[] decoded = decoderBase64.decode(publicKey);

        PublicKey priKey = KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(decoded));

        Signature signature = Signature
                .getInstance(SIGNATURE_ALGORITHM);

        signature.initVerify(priKey);
        signature.update(content.getBytes(StandardCharsets.UTF_8));
        return signature.verify(decoderBase64.decode(sign) );
    }
}

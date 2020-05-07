package com.jinhaoxun.dubbo.util.datautil;

/**
 * @Description: 进制转换工具类
 * @Author: jinhaoxun
 * @Date: 2020/4/1 下午11:04
 * @Version: 1.0.0
 */
public class BinaryUtil {

    /**
     * @Author: jinhaoxun
     * @Description: 将二进制转换成16进制
     * @param buf
     * @Date: 2020/4/2 上午12:45
     * @Return: java.lang.String
     * @Throws:
     */
    public static String convertByte2ToHexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * @Author: jinhaoxun
     * @Description: 将16进制转换为二进制
     * @param hexStr
     * @Date: 2020/4/2 上午12:45
     * @Return: java.lang.String
     * @Throws:
     */
    public static byte[] convertHexStrToByte2(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


    /**
     * @Author: jinhaoxun
     * @Description: 将字符串转换为二进制
     * @param str
     * @Date: 2020/4/2 上午12:45
     * @Return: java.lang.String
     * @Throws:
     */
    public static String convertStringToByte2(String str){
        //把字符串转成字符数组
        char[] strChar=str.toCharArray();
        String result="";
        for(int i=0;i<strChar.length;i++){
            //toBinaryString(int i)返回变量的二进制表示的字符串
            //toHexString(int i) 八进制
            //toOctalString(int i) 十六进制
            result +=Integer.toBinaryString(strChar[i])+ " ";
        }
        return result;
    }

    /**
     * @Author: jinhaoxun
     * @Description: 将二进制字符串转换成int数组
     * @param binStr
     * @Date: 2020/4/2 上午12:45
     * @Return: java.lang.String
     * @Throws:
     */
    public static int[] convertByte2ToArray(String binStr) {
        char[] temp=binStr.toCharArray();
        int[] result=new int[temp.length];
        for(int i=0;i<temp.length;i++) {
            result[i]=temp[i]-48;
        }
        return result;
    }

    /**
     * @Author: jinhaoxun
     * @Description: 将二进制字符串转换成字符串
     * @param b
     * @Date: 2020/4/2 上午12:45
     * @Return: java.lang.String
     * @Throws:
     */
    public static String convertByte2ToString(byte[] b)
    {
        StringBuffer sb = new StringBuffer();
        String tmp = "";
        for (int i = 0; i < b.length; i++) {
            tmp = Integer.toHexString(b[i] & 0XFF);
            if (tmp.length() == 1){
                sb.append("0" + tmp);
            }else{
                sb.append(tmp);
            }

        }
        return sb.toString();
    }
}

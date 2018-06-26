package ffyyapi_webapp;

import java.io.IOException;

import com.google.zxing.WriterException;

import cn.com.ffxl.qrcode.QRCodeFactory;

public class QRCodeTest {
  public static void main(String[] args) {

    String content = "大家好，我是李庆文，很高兴认识大家";
    String logUri = "D:/jiawei/飞凡夜语/飞凡心理APP/需求/平台logo/64.png";
    String outFileUri = "D:qrcode.jpg";
    int[] size = new int[] { 430, 430 };
    String format = "jpg";

    try {
      new QRCodeFactory().CreatQrImage(content, format, outFileUri, logUri, size);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (WriterException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyphersorce.tscprint;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 *
 * @author NS
 */
public class NewClass {

    public interface TscLibDll extends Library {

        TscLibDll INSTANCE = (TscLibDll) Native.loadLibrary("../lib/TSCLIB", TscLibDll.class);

        int about();

        int openport(String pirnterName);

        int closeport();

        int sendcommand(String printerCommand);

        int sendBinaryData(byte[] printerCommand, int CommandLength);

        int setup(String width, String height, String speed, String density, String sensor, String vertical, String offset);

        int downloadpcx(String filename, String image_name);

        int barcode(String x, String y, String type, String height, String readable, String rotation, String narrow, String wide, String code);

        int printerfont(String x, String y, String fonttype, String rotation, String xmul, String ymul, String text);

        int clearbuffer();

        int printlabel(String set, String copy);

        int windowsfont(int x, int y, int fontheight, int rotation, int fontstyle, int fontunderline, String szFaceName, String content);

        int windowsfontUnicode(int x, int y, int fontheight, int rotation, int fontstyle, int fontunderline, String szFaceName, byte[] content);

        int windowsfontUnicodeLengh(int x, int y, int fontheight, int rotation, int fontstyle, int fontunderline, String szFaceName, byte[] content, int length);

        byte usbportqueryprinter();

    }

    public static void main(String[] args) {

        //TSCLIB_DLL.about();
        byte status = TscLibDll.INSTANCE.usbportqueryprinter();//0 = idle, 1 = head open, 16 = pause, following <ESC>!? command of TSPL manual
        TscLibDll.INSTANCE.openport("TSC TTP-244 Pro");
        TscLibDll.INSTANCE.sendcommand("SIZE 100 mm, 14 mm");
        TscLibDll.INSTANCE.sendcommand("SPEED 4");
        TscLibDll.INSTANCE.sendcommand("DENSITY 12");
        TscLibDll.INSTANCE.sendcommand("DIRECTION 1");
        TscLibDll.INSTANCE.sendcommand("SET TEAR ON");
        TscLibDll.INSTANCE.sendcommand("CODEPAGE UTF-8");
        TscLibDll.INSTANCE.sendcommand("GAP 3 mm, 0 mm");
        TscLibDll.INSTANCE.clearbuffer();
        TscLibDll.INSTANCE.sendcommand("TEXT 333,102,\"2\",270,2,2,\"JAJ\"");
        TscLibDll.INSTANCE.sendcommand("TEXT 374,24,\"0\",0,8,8,\"MC: 50.00 /G\"");
        TscLibDll.INSTANCE.sendcommand("TEXT 374,54,\"0\",0,8,8,\"WAS: 13.00%\"");
        TscLibDll.INSTANCE.sendcommand("TEXT 374,84,\"0\",0,8,8,\"WT: 12.170\"");
        TscLibDll.INSTANCE.sendcommand("TEXT 500,104,\"2\",270,1,1,\"916 HM\"");
        TscLibDll.INSTANCE.sendcommand("BARCODE 553,24, \"93\",30,2,0,2,4,\"CHA001\"");
        TscLibDll.INSTANCE.sendcommand("TEXT 553,84,\"0\",0,9,9,\"SILKY ROPE CHAIN\"");
        TscLibDll.INSTANCE.printlabel("1", "1");
        TscLibDll.INSTANCE.closeport();

    }

}

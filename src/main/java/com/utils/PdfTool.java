/**
 * Project Name:hotel-commons
 * File Name:Pdf2WordTool.java
 * Package Name:com.java.utils
 * Date:上午10:21:09
 * Copyright (c) 2019, bluemobi All Rights Reserved.
 *
*/

package com.utils;

import java.util.Date;

/**
 * Description: Date: 上午10:25:57 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
public class PdfTool {
    public static void main(String[] args) {
        Date before=new Date(1652273978557L);
        Date now=new Date();
        System.out.println(now.compareTo(before));//1652273978557 之前

    }

}

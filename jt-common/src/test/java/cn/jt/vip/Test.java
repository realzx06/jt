package cn.jt.vip;

import java.io.File;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("简易记事本");
        System.out.println("请输入文件名(数字，大小写字母，下划线组成)：");
        String regex = "\\w+";
        String fileName = scanner.nextLine();
        File file = new File(fileName + ".txt");

        while (!fileName.matches(regex) || file.exists()) {
            System.out.println(fileName);
            if (!fileName.matches(regex)) {
                System.out.print("命名格式不对，请重新输入：");
                fileName = scanner.nextLine();
            } else {
                System.out.print("文件已存在，是否覆盖(1是，2否)：");
                int a = scanner.nextInt();
                if (a == 1) {
                    break;
                } else if (a == 2) {
                    System.out.println("请重新输入文件名：");
                    fileName = scanner.nextLine();
                    continue;
                } else {
                    System.out.println("输入有误！！！");
                }
            }
        }
    }
}

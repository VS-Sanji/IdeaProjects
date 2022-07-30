package lianxiti;

import java.io.*;

/**
 *
 拷贝目录：
 将D:/a拷贝到C盘根下....

 需要使用到：
 FileInputStream
 FileOutputStream
 File

 可能需要使用到递归。你尝试实现一下！
 */
public class LianXi34 {
    public static void main(String[] args) {
        //采用面向对象的思路取处理，这里要求拷贝一个目录到另一个目录下，那么对象就是这两个目录，拷贝的过程单独写一个方法来完成
        //拷贝源
        File srcFile = new File("D:\\a\\b\\c\\e\\f\\g\\Mysql");
        //拷贝目标
        File destFile = new File("C:\\");

        //有了两个对象，然后就是拷贝的过程，具体怎么实现，在方法体中解决
        copyFile(srcFile,destFile);

    }

    //这个方法就负责拷贝的具体实现
    private static void copyFile(File srcFile, File destFile) {
        //递归需要考虑结束条件，否则无法结束，我们在该方法起始位置进行判断
        //结束条件在这就是：如果作为拷贝源的是一个具体文件，那么就结束递归。因为递归逻辑相同，只考虑一轮，考虑最初的一轮：开始的是srcFile，判断它是不是具体文件即可
        if (srcFile.isFile()) {
            //如果条件成立，递的过程就结束，往回归还。
            //进入到这说明它是具体文件，我们需要对其进行拷贝

            //创建文件输入，输出流
            FileInputStream in = null;
            FileOutputStream out = null;
            try {
                in = new FileInputStream(srcFile);//这个需要一个输入源，给一个路径，以字符串的形式给可以；这里以File的形式给也可以，srcFile对象包含文件的路径信息
                out = new FileOutputStream(srcFile.getAbsolutePath().endsWith("\\") ? destFile.getAbsolutePath() + srcFile.getAbsolutePath().substring(3) : destFile.getAbsolutePath() + "\\" + srcFile.getAbsolutePath().substring(3));
                //这个需要一个输出源，给一个路径，这个路径可以根据输入源路径和目标位置进行拼接得到。
                byte[] bytes = new byte[1024 * 1024];
                int readCount = 0;
                while ((readCount = in.read(bytes)) != -1){
                    out.write(bytes,0,readCount);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return;
        }


        /**拷贝源传进来，第一步先看看里面有什么，获取它们*/
        File[] files = srcFile.listFiles();//listFiles方法获取了它们，并放在了File数组中
        //先不急写下一步，我们可以先测试一下数组中元素个数，写一步测一下
        //System.out.println(files.length);//7  知道数组中有7个元素

        //有可能在File数组中还有一部分是 目录，一部分是 文件，那我们可以遍历看看
        for (File file : files){
            //数组中的 目录对象可能其下还有子目录，需要往下继续把其下的内容拷贝，逻辑和当前一样，那我们就可以进行递归
            //在递归之前，我们可以先进行判断，如果file对应的是一个目录，我们可以把他的绝对路径拿到，在目标盘符下创建对应的目录，这样就实现了目录的拷贝
            if (file.isDirectory()) {
                String destDir = destFile.getAbsolutePath().endsWith("\\") ? destFile.getAbsolutePath()+ file.getAbsolutePath().substring(3) : destFile.getAbsolutePath() + "\\" + file.getAbsolutePath().substring(3);
                //这里的C：\\修改为destFile.getAbsolutePath() ，是因为不一定就是拷贝到C:\\下，我们需要拷到哪就是哪，所以在这里获取 destFile的路径就达到了通用的效果
                //另外还要注意是:如果通过getAbsolutePath()获取的路径是以 "\\" (斜杠)结尾,就直接连上截取的部分,如果不是以"\\"结尾,就加一个斜杠在连接截取的部分
                //System.out.println(destDir); 测试
                File newFile = new File(destDir);//destDir是一个字符串，我们要将其抽象为文件或者目录的路径名，需要调用File的构造方法，他就会解析成路径名
                if (!newFile.exists()) {
                    newFile.mkdirs();//将目录创建出来
                }
            }
            //递归：自己调用自己 因为是同一个方法，所以具体实现逻辑相同，这个file现在就相当于是srcFile了，destFile也是子目录了，写了这个递归后把该方法的实现逻辑补充完善即可，其他不用管了（因为是相同的）
            //递归需要考虑结束条件，否则无法结束，我们在该方法起始位置进行判断
            copyFile(file,destFile);

        }
    }
}

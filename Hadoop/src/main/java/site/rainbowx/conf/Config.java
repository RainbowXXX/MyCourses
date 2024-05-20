package site.rainbowx.conf;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author yan32
 * createTime: 2024/05/06 14:02
 * description:  The properties of hadoop
 */
public class Config {
    public static String PropertyFilePath = "src/main/resources/hadoop.properties";

    public static Configuration conf;
    public static Properties properties;

    static {
        try {
            // 加载配置文件
            FileInputStream fis = new FileInputStream(PropertyFilePath);
            properties = new Properties();
            properties.load(fis);

            // 读取配置项
            String url = properties.getProperty("hadoop.datasource.url");
            String username = properties.getProperty("hadoop.datasource.username");

            conf = new Configuration();
            conf.set("fs.defaultFS", url);

            System.setProperty("HADOOP_USER_NAME", username);
            FileSystem fs = FileSystem.get(conf);
            System.out.println("Hadoop connect ok!");
            fs.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}

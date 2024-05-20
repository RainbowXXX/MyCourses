package site.rainbowx;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import site.rainbowx.Mapper.WordCountMapper;
import site.rainbowx.Reducer.WordCountReducer;
import site.rainbowx.conf.Config;
import org.apache.hadoop.fs.FileSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author yan32
 * createTime: 2024/05/06 13:48
 * description: ${description}
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        FileSystem fs = FileSystem.get(Config.conf);

        if(args.length<2){
            return;
        }
        switch (args[0]) {
            case "cat": {
                Path path = new Path(args[1]);
                InputStreamReader inputStreamReader = new InputStreamReader(fs.open(path));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    System.out.println(line);
                }
                break;
            }
            case "ls": {
                Path path = new Path(args[1]);
                FileStatus[] statusList = fs.listStatus(path);
                for (FileStatus status : statusList) {
                    System.out.println(status.getPath().getName());
                }
                break;
            }
            case "rm": {
                Path path = new Path(args[1]);
                fs.delete(path);
                break;
            }
            case "wc": {
                Job job = Job.getInstance(Config.conf, "Word count");

                job.setJarByClass(Main.class);
                job.setMapperClass(WordCountMapper.class);
                job.setReducerClass(WordCountReducer.class);

                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(IntWritable.class);

                FileInputFormat.addInputPath(job, new Path(args[1]));
                FileOutputFormat.setOutputPath(job, new Path(args[2]));

                System.exit(job.waitForCompletion(true) ? 0 : 1);
                break;
            }
        }


        fs.close();
    }
}
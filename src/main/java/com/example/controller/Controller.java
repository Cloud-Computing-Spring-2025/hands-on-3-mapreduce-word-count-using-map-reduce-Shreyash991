package com.example.controller;
import com.example.WordMapper;
import com.example.WordReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.io.IOException;


public class Controller{

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
          Configuration config=new Configuration();
          Job job=Job.getInstance(config,"Word count");

          job.setJarByClass(Controller.class);
          job.setMapperClass(WordMapper.class);
          job.setReducerClass(WordReducer.class);

          job.setOutputKeyClass(Text.class);
          job.setOutputValueClass(IntWritable.class);

          FileInputFormat.addInputPath(job,new Path(args[0]));
          FileOutputFormat.setOutputPath(job,new Path(args[1]));

          System.exit(job.waitForCompletion(true)?0:1);
  
    }
}
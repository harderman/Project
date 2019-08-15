package com.hl.commands;

import com.hl.Command;
import com.hl.Database;
import com.hl.Protocol;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
//返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定。
// 其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。
// 你也可以使用负数下标，以 -1 表示列表的最后一个元素，
// -2 表示列表的倒数第二个元素，以此类推。
public class LRANGECommand implements Command {
    private List<Object> args;

    @Override
    public void setArgs(List<Object> args) {
        this.args = args;
    }

    @Override
    public void run(OutputStream os) throws IOException {
        String key = new String ((byte[]) args.get(0));
        int start = Integer.parseInt(new String ((byte[]) args.get(1)));
        int end = Integer.parseInt(new String ((byte[]) args.get(2)));
        List<String> list = Database.getList(key);
        if(end<0){
            end = list.size()+end;
        }
        List<String> result = list.subList(start,end+1);
        try {
            Protocol.writeArray(os,result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

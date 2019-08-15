package com.hl.commands;

import com.hl.Command;
import com.hl.Database;
import com.hl.Protocol;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

//命令用于返回哈希表中指定字段的值。
public class HGETCommand implements Command {
    private List<Object> args;
    @Override
    public void setArgs(List<Object> args) {
        this.args = args;
    }

    @Override
    public void run(OutputStream os) throws IOException {
        String key = new String((byte[]) args.get(0));
        String filed = new String((byte[]) args.get(1));
        Map<String,String> hash = Database.getHash(key);
        String value = hash.get(filed);
        if(value != null){
            Protocol.writeBulkString(os,value);
        }
        else{
            Protocol.writeNull(os);
        }
    }
}

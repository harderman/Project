package com.hl.commands;

import com.hl.Command;
import com.hl.Database;
import com.hl.Protocol;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

//用于为哈希表中的字段赋值。
//如果哈希表不存在,一个新的哈希表被创建并进行 HSET 操作。
//如果字段已经存在于哈希表中，旧值将被覆盖。
public class HSETCommand implements Command {
    private List<Object> args;
    @Override
    public void setArgs(List<Object> args) {
        this.args = args;
    }

    @Override
    public void run(OutputStream os) throws IOException {
        String key = new String((byte[]) args.get(0));
        String filed = new String ((byte[]) args.get(1));
        String value = new String ((byte[]) args.get(2));
        Map<String,String > map = Database.getHash(key);
        boolean IsUpdate = map.containsKey(key);
        map.put(filed,value);
        if(IsUpdate){
            Protocol.writeInteger(os,0);
        }else{
            Protocol.writeInteger(os,1);
        }
    }
}

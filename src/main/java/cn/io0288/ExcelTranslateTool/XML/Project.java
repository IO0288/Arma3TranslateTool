package cn.io0288.ExcelTranslateTool.XML;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Project {
    @NonNull
    private String name;
    private List<Package> packages;

}
@Getter
@Setter
class Package{
    @NonNull
    private String name;
    private List<Key> packages;

}
@Getter
@Setter
class Key{
    @NonNull
    private String ID;
    private HashMap<String,String> KV;
}

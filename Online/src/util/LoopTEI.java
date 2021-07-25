package util;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

public class LoopTEI extends TagExtraInfo {
    // 覆盖方法，定义脚本变量的信息
    public VariableInfo[] getVariableInfo(TagData data) {
        return new VariableInfo[] { new  VariableInfo(data.getAttributeString("name"), data.getAttributeString("type"), true, VariableInfo.NESTED)};
    }
}

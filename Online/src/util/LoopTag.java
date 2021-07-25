package util;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class LoopTag extends BodyTagSupport {
    private String name;// 迭代出的对象在pageContext中的名字
    private Collection collection;// 需要迭代的集合对象
    private Iterator it;// 需要迭代的对象
    private String type;// 在迭代器中对象的类型

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    // 将页面中的集合对象传入到程序中
    public void setCollection(Collection collection) {
        this.collection = collection;
        // 生成迭代对象
        if (collection.size() > 0) {
            it = collection.iterator();
        }
    }

    public int doStartTag() throws JspException {
        // 如果集合没有内容，不执行标签体
        if (it == null) {
            return this.SKIP_BODY;
        }
        return this.EVAL_BODY_INCLUDE;
    }

    public int doAfterBody() throws JspException {
        if (it.hasNext()) {
            //在这里自定义输出的内容
            pageContext.setAttribute(name, "<br/>" + it.next());
            return this.EVAL_BODY_AGAIN;// 此返回值将反复调用此方法
        }
        return this.SKIP_BODY;
    }

    public int doEndTag() throws JspException {
        if (bodyContent != null) {
            try {
                bodyContent.writeOut(bodyContent.getEnclosingWriter());
            } catch (IOException e) {
                throw new JspException("IO Error " + e.getMessage());
            }
        }
        return this.EVAL_PAGE;
    }
}

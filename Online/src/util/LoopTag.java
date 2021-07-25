package util;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class LoopTag extends BodyTagSupport {
    private String name;// �������Ķ�����pageContext�е�����
    private Collection collection;// ��Ҫ�����ļ��϶���
    private Iterator it;// ��Ҫ�����Ķ���
    private String type;// �ڵ������ж��������

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    // ��ҳ���еļ��϶����뵽������
    public void setCollection(Collection collection) {
        this.collection = collection;
        // ���ɵ�������
        if (collection.size() > 0) {
            it = collection.iterator();
        }
    }

    public int doStartTag() throws JspException {
        // �������û�����ݣ���ִ�б�ǩ��
        if (it == null) {
            return this.SKIP_BODY;
        }
        return this.EVAL_BODY_INCLUDE;
    }

    public int doAfterBody() throws JspException {
        if (it.hasNext()) {
            //�������Զ������������
            pageContext.setAttribute(name, "<br/>" + it.next());
            return this.EVAL_BODY_AGAIN;// �˷���ֵ���������ô˷���
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

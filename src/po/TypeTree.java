package po;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TypeTree {

    private TypeTree next = null;
    //存子节点
    private List<TypeTree> childrens = new ArrayList<TypeTree>();
    private int rangepre = 0;
    private int rangenex = 0;
    private int typeIndex;

    public Boolean hasNext(){
        if(next == null)
            return false;
        return true;
    }

    public TypeTree next(){
        return next;
    }

    public TypeTree getNext() {
        return next;
    }

    public void setNext(TypeTree next) {
        this.next = next;
    }

    public int getRangepre() {
        return rangepre;
    }

    public void setRangepre(int rangepre) {
        this.rangepre = rangepre;
    }

    public int getRangenex() {
        return rangenex;
    }

    public void setRangenex(int rangenex) {
        this.rangenex = rangenex;
    }

    public List<TypeTree> getChildrens() {
        return childrens;
    }

    public void addChildrens(TypeTree childrens) {
        this.childrens.add(childrens);
    }

    public int getFanum() {
        return childrens.size();
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(int typeIndex) {
        this.typeIndex = typeIndex;
    }

    public TypeTree createTree(TypeTree treeroot, int per, int total, String sta){
        //平分层次
        int layer = total / per;
        //00舍弃，从01开始算
        int pre = 1;
        //00舍弃，从01开始算
        int nex = 1;

        //获取低位不变二进制位
        String b_next = "";
        for(int j =1 ; j < layer; ++j){
            b_next += repeatCreate("0", per);
        }

        //获取分类个数
        String fa_s = repeatCreate("1", per);

        //开始建树，该层一共会分出2（per）次方-1个类
        for(int i = 0; i < Integer.parseInt(new BigInteger(fa_s, 2).toString()); ++i){

            //如果分层为1，说明这层的节点是叶子节点，他们没有子类
            if(layer == 1)
                return null;

            //获取2进制字符串
            String b_pre = Integer.toBinaryString(pre);
            if(b_pre.length() < per)
                b_pre = repeatCreate("0", per - b_pre.length()) + b_pre;

            //拼接2进制
            String s = sta + b_pre + b_next;


            TypeTree fa = new TypeTree();
            //主类id
            fa.setTypeIndex(Integer.parseInt(new BigInteger(s, 2).toString()));

            //子类范围前驱
            fa.setRangepre(fa.getTypeIndex() + 1);

            b_pre = Integer.toBinaryString(pre+1);
            if(b_pre.length() < per)
                b_pre = repeatCreate("0", per - b_pre.length()) + b_pre;

            //子类范围后驱
            s = sta + b_pre + b_next;
            fa.setRangenex(Integer.parseInt(new BigInteger(s,2).toString()) - 1);

            String childrenSta = Integer.toBinaryString(pre);
            if(childrenSta.length() < per){
                childrenSta = repeatCreate("0",per - childrenSta.length()) + childrenSta;
            }

            //深度搜索设定子节点
            createTree(fa, per, total - per, sta+childrenSta);

            treeroot.addChildrens(fa);

            ++pre;
        }

        return this;
    }

    public String repeatCreate(String r, int n){
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < n; ++i){
            str.append(r);
        }
        return str.toString();
    }

    public TypeTree getRange(int id){
        for(TypeTree i : childrens){
            if(i.getTypeIndex() == id)
                return i;
            if(id >= i.getRangepre() && id <= i.getRangenex()) {
                TypeTree temp = i.getRange(id);
                if(temp != null)
                    return temp;
                else if(temp == null)
                    return i;
            }
        }
        return null;
    }

    public int getChildrenIndex(int typeIndex){
        int n =0;
        for(TypeTree i : this.childrens){
            ++n;
            if(i.getTypeIndex() == typeIndex)
                return (childrens.get(n)).getTypeIndex();
        }
        return -1;
    }


}

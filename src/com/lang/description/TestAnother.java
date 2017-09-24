package com.lang.description;

/**
 * 1.常量池中的数据都是同一个对象
 * 2.一旦有变量赋值操作就不是同一对象
 * 
 * 编译器优化处理
 * 当遇到“”会自动去除
 * 有String a="s";相当于编译器String a=new String("s");
 * @author lang
 *
 */
public class TestAnother {

	public static void main(String[] args) {
		String s="s"+"1"+"s";
		String b="s"+"1"+new String("s");
		//System.out.println(s==b);
		
		String s1="s"+"";
		String s2=s1+"";
		
		String s3="s"+""+"";
		System.out.println(s3==s2);
		System.out.println(s3==s1);
		String b1="s"+"";
		//System.out.println(s1==b1);
		
		
		
		print("a","","a","");
	}
	
	public static void print(String key1,String key2,String key3,String key4){
		System.out.println(key1==key3);
		System.out.println(key2==key4);
		
		String a=key1+key2;
		String b=key3+key4;
		String a1="a"+"";
		String b1="a"+"";
		String a2="a"+key2;
		String b2="a"+key2;
		System.out.println(a==b);
		System.out.println(a1==b1);
		System.out.println(a2==b2);
	}
}

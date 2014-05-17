package lab9;

import java.util.NoSuchElementException;

/**
 * Implements a Map as a binary search tree of (key,value) pairs.
 * CSE131 Lab 9
 * @author
 * @version 1.0
 * Date:
 */
public class TreeMap<K extends Comparable<K>,V> implements Map<K,V> {
	TreeNode<K,V> head;
	String full = "";
	
	TreeMap(){
		this.head = null;
	}
	
	
	
	public void put(K key, V value) {
		TreeNode<K, V> node = new TreeNode<K,V>(key, value);
		TreeNode<K,V> m = head;
		
		if(head == null) {
			head = node;
		}
		else {
			boolean added = false;
			while(m.key.compareTo(key)!=0) {
				if(m.key.compareTo(key)>0) {
					if(m.left == null) {
						m.left = node;
						added = true;
						break;
					} 
					m = m.left;
				} 
				else {
					if(m.key.compareTo(key)<0) {
						if(m.right == null) {
							m.right = node;
							added = true;
							break;
						} 
						m = m.right;
					} 
				}
			}
			if(m.key.compareTo(key)==0) {
				m.value = node.value;
				added = true;
			} 
		}
	}
//	public void put(K key, V value) {
//		TreeNode<K, V> node = new TreeNode<K,V>(key, value);
//		TreeNode<K,V> m = head;
//		
//		if(head == null)
//			head = node;
//		
//		else{
//			while(m.left != null || m.right != null){
//				if(m.key.compareTo(key) == 0){
//					m.value = node.value;
//					break;
//				}
//				else if(m.key.compareTo(key)<0){
//					if(m.right == null){
//						m.right = node;
//						break;
//					}
//					else
//						m=m.right;
//				}
//				else if(m.key.compareTo(key)>0){
//					if(m.left == null){
//						m.right = node;
//						break;
//					}
//					else{
//						m=m.left;
//					}
//				}
//			}
//		}
//
//	}

	

	public V get(K key) {
		TreeNode<K,V> m = head;
		boolean added = false;
		if(contains(key)==false)
			throw new NoSuchElementException();
		while(m.key.compareTo(key)!=0) {
			if(m.key.compareTo(key)>0) {
				if(m.left.key.compareTo(key)==0) {
					return m.left.value;
				} 
				m = m.left;					
			} 
			else {
				if(m.right.key.compareTo(key)==0) {
					return m.right.value;
				} 
				m = m.right;
			}
		}
		return m.value;
	}
		
		
		
		
		
//		else{
//			if(m.key.compareTo(key) == 0){
//				return m.value;
//			}
//			else if(m.key.compareTo(key)<0){
//				m=m.right;
//				return get(key);
//			}
//			else if(m.key.compareTo(key)>0){
//				m=m.left;
//				return get(key);
//			}
//		}	
//		return null;

	public boolean contains(K key) {
		TreeNode<K,V> m = head;
		if(m.key.compareTo(key)==0)
			return true;
		while(m.key.compareTo(key)!=0) {
			if(m.key.compareTo(key)>0) {
				if(m.left == null)
					return false;
				if(m.left.key.compareTo(key)==0) {
					if(m.deleted == false)
						return true;
					else
						return false;
				} 
				m = m.left;					
			} 
			else if(m.key.compareTo(key)<0){
				if(m.right == null)
					return false;
				if(m.right.key.compareTo(key)==0)
					if(m.deleted == false)
						return true;
					else
						return false;
				m = m.right;
			}
		}
		return false;
	}
		
//		TreeNode<K,V> m = head;
//		
//		if(m.key.compareTo(key) == 0){
//			System.out.println("found key");
//			return true;
//		}
//		
//		else if(m.key.compareTo(key)<0){
//			if(m.right == null)
//				return false;
//			else{
//				m=m.right;
//				return contains(key);
//			}
//		}
//		else if(m.key.compareTo(key)>0){
//			if(m.left == null)
//				return false;
//			else{
//				m=m.left;
//				return contains(key);
	//			}
	//		}
	//		
	//		return false;

	public boolean remove(K key)
	{
		head = remove(head, key);
		return true;
	}
	private TreeNode<K,V> remove(TreeNode<K,V> p, K key)
	{
		if (p == null)  
			throw new RuntimeException("Nothing in the list");
		else
			if (key.compareTo(p.key) < 0)
				p.left = remove(p.left, key);
			else if (key.compareTo(p.key) > 0)
				p.right = remove (p.right, key);
			else
			{
				if (p.left == null) 
					return p.right;
				else if (p.right == null) 
					return p.left;
				else
				{
					// get data from the rightmost node in the left subtree
					p.key = retrieveData(p.left);
					// delete the rightmost node in the left subtree
					p.left =  remove(p.left, p.key) ;
				}
			}
		return p;
	}
	private K retrieveData(TreeNode<K, V> p)
	{
		while (p.right != null) 
			p = p.right;
		return p.key;
	}

	public String toString() {
		String tabs = "";
		tabs = HelperString(tabs, head);
		return tabs;
	}
	
	public String HelperString(String tabs, TreeNode<K,V> pointer){
		String holder = "";
		if(pointer.right != null){
			holder = holder + HelperString(tabs + '\t', pointer.right);
		}
		
		if(pointer.deleted == false)
			holder = holder +tabs  + "(" + pointer.key + " " + pointer.value + ")" + '\n';
		else
			holder = holder +tabs  + "()" + '\n';
		
		if(pointer.left != null){
			holder = holder + HelperString (tabs + '\t', pointer.left);
		}
		return holder;
		
	}
	


}

//TreeNode<K,V> m = head;
//
//if(contains(key)==false)
//	throw new NoSuchElementException();
//while(m.key.compareTo(key)!=0) {
//	if(m.key.compareTo(key)>0) {
//		if(m.left.key.compareTo(key)==0) {
//			m.deleted = true; 
//			return true;
//		} 
//		m = m.left;					
//	} 
//	else {
//		if(m.right.key.compareTo(key)==0) {
//			m.deleted = true;
//			return true;
//		} 
//		m = m.right;
//	}
//}
//return false;

/*TreeNode<K,V> m = head;
if(head == null || contains(key)==false)		
	throw new NoSuchElementException();
else if(m.key.compareTo(key)==0){
	if(m.left==null && m.right==null){
		m=null;
		return true;
	}
}
else if((head.left==null && head.right !=null) || (head.right == null && head.left !=null)){
	if(head.left==null && head.right !=null){
		System.out.println("here");
		head = head.left;
		head.left = null;
		return true;
	}
	else{
		System.out.println("or here");
		head = head.right;
		head.left = null;
		return true;
	}
}

else{
	System.out.println("got to end else");
	while(m.key.compareTo(key)!=0) {
		if(m.key.compareTo(key)>0) {
			if(m.left.key.compareTo(key)==0) {
				m=m.left;
				break;
			} 
			m = m.left;					
		} 
		else {
			if(m.right.key.compareTo(key)==0) {
				m=m.right;
				break;
			} 
			m = m.right;
		}
	}
	if(m.left != null && m.right != null){
		if(m.left.right == null){
			m.left = head;
			m.left = null;
			return true;
		}
		m=m.left;
		while(m.right !=null){
			m=m.right;
		}
		if(m.right==null){
			m = head;
			return true;
		}
	}
	if(m.left==null && m.right ==null){
		m=null;
		return true;
	}
	if(m.left == null){
		m.toString();
		m=m.right;
		m.right = null;
		return true;
	}
	if(m.right == null){
		m.toString();
		m=m.left;
		m.left=null;
		return true;
	}
	
}	
return false;*/
	
//		if(m.key.compareTo(key)>0) {
//			if(m.left == null)
//				return false;
//			if(m.left.key.compareTo(key)==0) {
//				if(m.left.left==null && m.left.right==null){
//					m.left=null;
//					return true;
//				}
//				else if(m.left.left != null && m.left.right != null){
//						
//				}
//				else{
//					if(m.left.left == null){
//						m.left = m.left.right;
//					}
//					if(m.left.right == null){
//						m.left = m.left.left;
//					}
//				}
//			} 
//			m = m.left;					
//		} 
//		else if(m.key.compareTo(key)<0){
//			if(m.right == null)
//				return false;
//			if(m.right.key.compareTo(key)==0)
//				return true;
//			m = m.right;
//		}
//	}
//}
//return false;

package cn.south.toast.common.utils;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import cn.south.toast.common.dto.TreeDTO;

/**
 * 
 * @author huangbh
 *  date of 2018年4月4日
 */
public class TreeUtils {
	
	/**
	 * list转树结构
	 * @param list
	 * @return
	 */
	public static TreeDTO list2Tree(List<? extends TreeDTO> list){
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		
		TreeDTO root = null;
		int size = list.size();
		for (int i = 0; i < size; i++) {
			TreeDTO resource = list.get(i);
			if(resource.getParentId() == null){
				root = resource;
			}
			String id = resource.getId();
			
			for (int j = 0; j < size; j++) {
				TreeDTO re = list.get(j);
				if(id != null && id.equals(re.getParentId())){
					resource.getChildren().add(re);
				}
			}
		}
		return root;
	}
	
	public static List<TreeDTO> list2TreeList(List<? extends TreeDTO> list){
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		
		List<TreeDTO> rootList = new ArrayList<TreeDTO>();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			TreeDTO resource = list.get(i);
			if(resource.getParentId().equals("0")){
				rootList.add(resource);
			}
			String id = resource.getId();
			
			for (int j = 0; j < size; j++) {
				TreeDTO re = list.get(j);
				if(id != null && id.equals(re.getParentId())){
					resource.getChildren().add(re);
					resource.setHasSub(true);
				}
			}
		}
		return rootList;
	}
}

package app.disney.util;

import java.beans.JavaBean;
import java.util.List;
@JavaBean
public interface IMapper <T> {
	
	public List<T> mappingListPersonajes(List<T> list);
	

}

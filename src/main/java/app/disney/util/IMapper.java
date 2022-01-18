package app.disney.util;

import java.beans.JavaBean;
import java.util.List;
@JavaBean
public interface IMapper {
	
	public List<?> mappingListPersonajes(List<?> list);
	
	public List<?> mappingListMovie(List<?> list);
	

}

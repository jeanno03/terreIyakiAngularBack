package terreIyaki.tool;

import javax.persistence.Transient;

public class PageElement {
	
	private Long elements;
	private Long nbParPage;


	public PageElement() {
		super();
	}



	public PageElement(Long elements, Long nbParPage) {
		super();
		this.elements = elements;
		this.nbParPage = nbParPage;
	}


	public Long getElements() {
		return elements;
	}



	public void setElements(Long elements) {
		this.elements = elements;
	}



	public Long getNbParPage() {
		return nbParPage;
	}



	public void setNbParPage(Long nbParPage) {
		this.nbParPage = nbParPage;
	}

	//faux getter qui va retourner le nombre de page total
@Transient
	public int getPageTotal() {
		Long pageTotalLong=0L;
		int pageTotal=0;
		pageTotalLong = this.elements/ this.nbParPage;
		if((this.elements % this.nbParPage)>0){
			pageTotal = Integer.valueOf(String.valueOf(pageTotalLong))+1;
		}
		else if((this.elements % this.nbParPage)==0) {
			pageTotal = Integer.valueOf(String.valueOf(pageTotalLong));
		}	
return pageTotal;
	}



}

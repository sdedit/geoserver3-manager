package it.geosolutions.geoserver.rest.encoder;

import org.jdom.Element;

public class GSLayerGroupEncoder3 extends GSLayerGroupEncoder {

	private Element titleElem;

	@Override
	public void setName(String name) {
		nameElem = elem("name", name);
	}

	public void setTitle(String title) {
		titleElem = elem("title", title);
	}

	@Override
	public void addLayer(String layer) {
		addLayer(layer, null);
	}

	@Override
	public void addLayer(String layer, String styleName) {
		initPublishables("layers");
		publishablesElem.addContent(new Element("layer").setText(layer));
		Element style = new Element("style");
		stylesElem.addContent(style);
		if (styleName != null) {
			style.setText(styleName);
		}
	}
	

	public String toString() {
		addToRoot(titleElem);
		return super.toString();
	}

}

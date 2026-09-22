package it.geosolutions.geoserver.rest.encoder.gwc;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import it.geosolutions.geoserver.rest.encoder.utils.PropertyXMLEncoder;

public class GWCTileFormatEncoder extends PropertyXMLEncoder {

	private Element mimeFormats = new Element("mimeFormats");

	private Element gridSubsets = new Element("gridSubsets");

	private Element metaWidthHeight = new Element("metaWidthHeight");
	
	private String layerName;
	
	public GWCTileFormatEncoder(String layerName) {
		super("GeoServerLayer");
		this.layerName = layerName;
	}
		
	public void setMimeTypes (List<String> mimeTypes) {
		var formats = new ArrayList<>(List.of("image/png", "image/jpeg"));
		formats.addAll(mimeTypes);
		for (var format : formats) {
			var str = new Element("string");
			str.setText(format);
			mimeFormats.addContent(str);
		}
		for (var srid : List.of("EPSG:4326", "EPSG:900913")) {
			var gridSubset = new Element("gridSubset");
			var name = new Element("gridSetName");
			name.setText(srid);
			gridSubset.addContent(name);
			gridSubsets.addContent(gridSubset);

		}
		metaWidthHeight.addContent(new Element("int").setText("4"));
		metaWidthHeight.addContent(new Element("int").setText("4"));		
	}
	
	public String toString() {
		add("name", layerName);
		add("enabled", "true");

		addContent(mimeFormats);
		addContent(gridSubsets);
		addContent(metaWidthHeight);

		add("expireCache", "0");
		add("expireClients", "0");
		add("gutter", "0");
		var str = super.toString();
		return str;
	}

}
